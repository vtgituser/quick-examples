package com.vt;

import static java.util.stream.IntStream.range;

public class FindMinStream {
  public static void main(String[] args) {
    int minInt = range(11, 13).unordered().parallel().filter(i -> i % 2 == 0).min().getAsInt();
    System.out.println(minInt);
  }
}
