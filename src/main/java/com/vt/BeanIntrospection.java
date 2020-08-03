package com.vt;

import static java.beans.Introspector.getBeanInfo;
import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Map;

public class BeanIntrospection {

  public static void main(String[] args) {

    try {
      // invoke method to get value
      Map<String, String> beanMethods = stream(
          getBeanInfo(Emp.class, Object.class)
              .getPropertyDescriptors()
      )
          // filter out properties with setters only
          .filter(propertyDescriptor -> nonNull(propertyDescriptor.getReadMethod()))
          .collect(toMap(
              // bean property name
              PropertyDescriptor::getName,
              pd -> pd.getReadMethod().getName()));
      out.println(beanMethods);
    } catch (IntrospectionException e) {
      e.printStackTrace();
    }
  }
}
