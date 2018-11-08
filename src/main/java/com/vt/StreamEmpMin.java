package com.vt;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamEmpMin {
  public static void main(String[] args) {
    /*Emp[] emps =
    new Emp[] {
      new Emp(4, "Sergio", "Q"),
      new Emp(7, "Sergio", "P"),
      new Emp(11, "Sergio", "PRa"),
      new Emp(8, "Sergio", "PP"),
      new Emp(1, "Sergio", "G"),
      new Emp(2, "Sergio", "H"),
      new Emp(0, "Joe", "B"),
      new Emp(3, "Mike", "K"),
      new Emp(10, "Sergio", "PR"),
      new Emp(6, "Sergio1", "S"),
      new Emp(5, "Sergio", "R"),
      new Emp(9, "Sergio2", "PQ")
    };*/
    /*    int sergioId =
        Arrays.stream(emps)
            .parallel()
            .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
            .mapToInt(Emp::getId)
            .min()
            .getAsInt();
    System.out.println(sergioId);*/
    /*
    long nano = nanoTime();
        Optional<String> sergioLast =
            Arrays.stream(emps)
                .sorted(Comparator.comparingInt(Emp::getId))
    //            .parallel()
                .peek(e -> System.out.println("before filter "+e.getId()))
                .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
                .peek(e -> System.out.println("after filter "+e.getId()))
                            .findFirst()
    //            .min(Comparator.comparingInt(Emp::getId))
                .map(Emp::getLast);
        System.out.println(nanoTime()-nano);
        System.out.println(sergioLast.orElse("NotFound"));
    */

    /*    String lastSergio =
        stream(emps)
            .sorted(comparingInt(Emp::getId))
            //            .parallel()
            .peek(e -> System.out.println("before filter " + e.getId()))
            .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
            .peek(e -> System.out.println("after filter " + e.getId()))
            .max(comparingInt(Emp::getId))
            //            .min(Comparator.comparingInt(Emp::getId))
            .map(Emp::getLast)
            .orElse("Not Found");
    System.out.println(lastSergio);*/

    /*Map<Integer, List<Emp>> collect =
        stream(emps).sorted(comparingInt(Emp::getId)).collect(groupingBy(e -> e.getId() % 3));
    System.out.println(collect);
    collect
        .entrySet()
        .stream()
        .filter(
            entry ->
                entry
                    .getValue()
                    .stream()
                    .filter(emp -> emp.getFirst().equals("Sergio"))
                    .max(comparingInt(Emp::getId))
                    .isPresent())
        .findFirst();*/

    /*    final String[] lname = new String[] {"Not Found"};

    stream(emps)
        .sorted(comparingInt(Emp::getId))
        .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
        .collect(groupingBy(e -> e.getId() % 3))
        .entrySet()
        .stream()
        .findFirst()
        .ifPresent(
            es ->
                es.getValue()
                    .stream()
                    .max(comparingInt(Emp::getId))
                    .ifPresent(x -> lname[0] = x.getLast()));
    System.out.println(lname[0]);*/

    /* String sergio =
        stream(emps)
            .sorted(comparingInt(Emp::getId))
            .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
            .findFirst()
            .map(Emp::getId)
            .map(id -> stream(emps).filter(e -> (e.getId() % 3 == id % 3)).collect(toList()))
            .map(
                li ->
                    li.stream()
                        .sorted(comparingInt(Emp::getId))
                        .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
                        .max(comparingInt(Emp::getId))
                        .map(
                            e -> e.getFirst().concat(e.getLast()).concat(String.valueOf(e.getId())))
                        .orElse("Not Found"))
            .orElse("Not Found");
    System.out.println(sergio);

    String sergio1 =
        stream(emps)
            .sorted(comparingInt(Emp::getId))
            .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
            .findFirst()
            .map(Emp::getId)
            .map(
                id ->
                    stream(emps)
                        .filter(e -> (e.getId() % 3 == id % 3))
                        .filter(e -> e.getFirst().equalsIgnoreCase("Sergio"))
                        .collect(toList()))
            .map(
                li ->
                    li.stream()
                        .sorted(comparingInt(Emp::getId).reversed())
                        .findFirst()
                        .map(
                            e -> e.getFirst().concat(e.getLast()).concat(String.valueOf(e.getId())))
                        .orElse("Not Found"))
            .orElse("Not Found");
    System.out.println(sergio1);*/

    Emp[] emps =
        new Emp[] {
          new Emp(1, "4", "A"),
          new Emp(2, "2", "B"),
          new Emp(3, "1", "C"),
          new Emp(4, "1", "D"),
          new Emp(5, "4", "E"),
          new Emp(6, "2", "F"),
          new Emp(7, "3", "G"),
          new Emp(8, "1", "H"),
          new Emp(9, "3", "I"),
          new Emp(10, "4", "J"),
          new Emp(11, "2", "K"),
          new Emp(12, "1", "L")
        };
    /* int first = stream(emps)
    .collect(toMap(Emp::getFirst, emp -> emp, (a, b) -> a, LinkedHashMap::new)).values()
    .stream().filter(e -> e.getId() > 2).findFirst().map(Emp::getId).orElse(0);*/
    //   int first = stream(emps).filter(distinctByKey(Emp::getFirst)).filter(e -> e.getId() >
    // 2).findFirst().map(Emp::getId).orElse(0);
    Set first = stream(emps).collect(toCollection(() -> new TreeSet<>(comparing(Emp::getFirst))));
    System.out.println(first);
  }

  public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }
}
