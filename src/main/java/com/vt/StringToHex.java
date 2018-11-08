package com.vt;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toHexString;

public class StringToHex {
  public static void main(String[] args) {
    System.out.println(toHexString(parseInt("10001000", 2)));
  }
}
