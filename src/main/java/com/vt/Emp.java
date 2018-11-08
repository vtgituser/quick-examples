package com.vt;

public class Emp {
  private int id;
  private String first, last;

  public Emp(int id, String first, String last) {
    this.id = id;
    this.first = first;
    this.last = last;
  }

  public int getId() {
    return id;
  }

  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Emp{");
    sb.append("id=").append(id);
    sb.append(", first='").append(first).append('\'');
    sb.append(", last='").append(last).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
