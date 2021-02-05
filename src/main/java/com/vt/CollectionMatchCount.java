package com.vt;

import java.util.Arrays;
import java.util.List;

public class CollectionMatchCount {
  public static void main(String[] args) {
    int[] a1 = new int[] {1, 2, 3, 4};
    Integer[] a2 = new Integer[] {1, 2, 4};
    List<Integer> l = Arrays.asList(a2);
    System.out.println(Arrays.stream(a1).filter(l::contains).count());
  }
}
