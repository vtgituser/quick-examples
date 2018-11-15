package com.vt;

public enum ErrorCode {
  FR1011("MULTIPLE HEADERS 1"),
  FR1021("MULTIPLE HEADERS 2"),
  FR1031("MULTIPLE HEADERS 3"),
  FR1041("MULTIPLE HEADERS 4"),
  FR1051("MULTIPLE HEADERS 5"),
  FR1061("MULTIPLE HEADERS 6");

  private String description;

  ErrorCode(String description) {
    this.description = description;
  }

  public String description() {
    return description;
  }
}
