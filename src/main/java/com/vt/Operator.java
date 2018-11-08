package com.vt;

public enum Operator {
  EQUAL(new String[] {"=", "==", "==="}),
  NOT_EQUAL(new String[] {"!=", "<>"}),
  LESS_THAN(new String[] {"<"}),
  LESS_THAN_EQUAL(new String[] {"<="}),
  GREATER_THAN(new String[] {">"}),
  GREATER_THAN_EQUAL(new String[] {">="}),
  EXISTS(new String[] {"not null", "exists"}),
  NOT_EXISTS(new String[] {"is null", "not exists"}),
  MATCH(new String[] {"match"});

  private String[] value;

  Operator(String[] value) {
    this.value = value;
  }

  //  @JsonValue
  public String toStringOperator() {
    return value[0];
  }

  //  @JsonCreator
  public static Operator fromStringOperator(String stringOperator) {
    if (stringOperator != null) {
      for (Operator operator : Operator.values()) {
        for (String operatorString : operator.value) {
          if (stringOperator.equalsIgnoreCase(operatorString)) {
            return operator;
          }
        }
      }
    }
    return null;
  }
}
