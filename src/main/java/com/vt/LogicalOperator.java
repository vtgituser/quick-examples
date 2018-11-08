package com.vt;

import java.util.Objects;

public enum LogicalOperator {
  EQUALS("=") {
    @Override
    public boolean apply(String x1, String x2) {
      return Objects.equals(x1, x2);
    }
  },
  NOTEQUALS("<>") {
    @Override
    public boolean apply(String x1, String x2) {
      return !Objects.equals(x1, x2);
    }
  };
  // You'd include other operators too...

  private final String text;

  private LogicalOperator(String text) {
    this.text = text;
  }

  // Yes, enums *can* have abstract methods. This code compiles...
  public abstract boolean apply(String x1, String x2);

  @Override
  public String toString() {
    return text;
  }
}
