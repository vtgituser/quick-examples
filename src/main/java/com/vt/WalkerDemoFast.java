package com.vt;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Consumer;

public class WalkerDemoFast
{
  @FunctionalInterface
  interface NodeGetter {
    Node apply (Node node);
  }

  @FunctionalInterface
  interface NodesGetter {
    Collection<? extends Node> apply (Node node);
  }

  class ClassData
  {
    ArrayList<NodeGetter>  simple_getters   = new ArrayList<>();
    ArrayList<NodesGetter> compound_getters = new ArrayList<>();

    ClassData(Lookup lookup, Class<?> klass) throws Throwable
    {
      for (Method m: klass.getMethods())
      {
        // small performance boost
        m.setAccessible(true);

        // we only want getters
        if (m.getParameterCount() != 0) continue;

        // this method returns a subtype of `Node`
        if (Node.class.isAssignableFrom(m.getReturnType())) {
          simple_getters.add(compile_node_getter(lookup, m));
          continue;
        }

        // this method returns a subtype of `Collection`
        if (Collection.class.isAssignableFrom(m.getReturnType())) {
          ParameterizedType ret = ((ParameterizedType) m.getGenericReturnType());
          Type param = ret.getActualTypeArguments()[0];

          // this method returns a subtype of `Collection<? extends Node>`
          if (param instanceof Class<?>
              && Node.class.isAssignableFrom((Class<?>) param))
            compound_getters.add(compile_nodes_getter(lookup, m));

        }
      }
    }
  }

  private static NodeGetter compile_node_getter(Lookup lookup, Method method) throws Throwable
  {
    MethodHandle handle = lookup.unreflect(method);

    CallSite site = LambdaMetafactory.metafactory(
        lookup, "apply",
        MethodType.methodType(NodeGetter.class),
        MethodType.methodType(Node.class, Node.class),
        handle, handle.type());

    return (NodeGetter) site.getTarget().invoke();
  }

  private static NodesGetter compile_nodes_getter(Lookup lookup, Method method) throws Throwable
  {
    MethodHandle handle = lookup.unreflect(method);

    CallSite site = LambdaMetafactory.metafactory(
        lookup, "apply",
        MethodType.methodType(NodesGetter.class),
        MethodType.methodType(Collection.class, Node.class), // TODO
        handle, handle.type());

    return (NodesGetter) site.getTarget().invoke();
  }

  private Lookup lookup = MethodHandles.lookup();
  private HashMap<Class<?>, ClassData> class_data = new HashMap<>();

  public void walk (Node node, Consumer<Node> visitor) throws Throwable
  {
    visitor.accept(node);

    Class<?> klass = node.getClass();

    ClassData data = class_data.computeIfAbsent(klass, k ->  {
      try { return new ClassData(lookup, k); }
      catch (Throwable e) { throw new RuntimeException(e); }
    });

    for (NodeGetter getter: data.simple_getters)
    {
      Node child = getter.apply(node);
      if (child == null) continue;
      walk(child, visitor);
    }

    for (NodesGetter getter: data.compound_getters)
    {
      Collection<Node> children = Util.cast(getter.apply(node));
      if (children == null) continue;
      for (Node child: children)
        walk(child, visitor);
    }
  }
}
