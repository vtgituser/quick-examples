package com.vt;

import java.util.Arrays;

public class RegExTest {
  public static void main(String[] args) {
    String[] split = "".trim().split("(?<=\\G.{4})");
    System.out.println(split.length);
    System.out.println(Arrays.toString(split));
  }
}
