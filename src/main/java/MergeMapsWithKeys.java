import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import reactor.util.function.Tuples;

public class MergeMapsWithKeys {
  public static void main(String[] args) {
    new MergeMapsWithKeys().merge();
  }

  private void merge() {

    String[] s = new String[] {"A", "B", "C"};
    HashMap<String, Object> map = new HashMap<>();
    map.put("hello", "HELLO");

    Stream.of("APP", "SPRING")
        .map(k -> Tuples.of(k, Optional.of(getMap())))
        .map(
            t2 ->
                t2.getT2()
                    .map(
                        m ->
                            m.entrySet()
                                .stream()
                                .collect(
                                    Collectors.toMap(
                                        k -> t2.getT1() + "." + k.getKey(), Map.Entry::getValue))))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(System.out::println);
  }

  private Map<String, Object> getMap() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("hello", "HELLO");
    return map;
  }
}
