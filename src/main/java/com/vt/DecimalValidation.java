package com.vt;

public class DecimalValidation {
  public static void main(String[] args) {
    // 5.0E-4
    System.out.println(Double.parseDouble("5.0E-4"));
    System.out.println(Double.parseDouble("NaN"));
    System.out.println(Double.parseDouble("-NaN"));
    System.out.println(Double.parseDouble("Infinity"));
    System.out.println(Double.parseDouble("-Infinity"));
    // System.out.println(Double.parseDouble(""));
    System.out.println(Double.parseDouble("null"));
    System.out.println(Double.parseDouble(null));
    // NaN
  }
}
