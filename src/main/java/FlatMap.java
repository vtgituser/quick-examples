import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

  public static Stream<Object> flatten(Object o) {
    if (o instanceof Map<?, ?>) {
      return ((Map<?, ?>) o).values().stream().flatMap(FlatMap::flatten);
    }
    return Stream.of(o);
  }

  public static Stream<Map.Entry<?, ?>> flatten2(Map.Entry<?, ?> entry) {
    if (entry.getValue() instanceof Map<?, ?>) {
      return ((Map<?, ?>) entry.getValue())
          .entrySet()
          .stream()
          .flatMap(
              e1 ->
                  flatten2(
                      new AbstractMap.SimpleEntry<>(
                          entry.getKey() + "." + e1.getKey(), e1.getValue())));
    }
    return Stream.of(entry);
  }

  public static void main(String[] args) {
    Map<String, Object> map0 = new TreeMap<>();
    map0.put("1", "value1");
    map0.put("2", "value2");
    Map<String, Object> map1 = new TreeMap<>();
    map0.put("3", map1);
    map1.put("1", "value3.1");
    map1.put("2", "value3.2");
    Map<String, Object> map2 = new TreeMap<>();
    map1.put("3", map2);
    map2.put("1", "value3.3.1");
    map2.put("2", "value3.3.2");

    List<Object> collect =
        map0.values().stream().flatMap(FlatMap::flatten).collect(Collectors.toList());
    // or
    List<Object> collect2 = flatten(map0).collect(Collectors.toList());
    System.out.println(collect);

    Map<?, ?> collect1 =
        map0.entrySet()
            .stream()
            .flatMap(FlatMap::flatten2)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    System.out.println(collect1);
  }
}
