package com.vt;

public class OperatorTest {
  public static void main(String[] args) {
    System.out.println(LogicalOperator.EQUALS.apply("2", "2"));
    System.out.println(LogicalOperator.NOTEQUALS.apply("2", "2"));
  }
}
