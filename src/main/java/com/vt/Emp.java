package com.vt;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Emp {
  private int id;
  private String first, last;
  private boolean active;
  private Emp manager;

  public Emp(int id, String first, String last, boolean active) {
    this.id = id;
    this.first = first;
    this.last = last;
    this.active = active;
  }

  public Emp(int id, String first, String last) {
    this.id = id;
    this.first = first;
    this.last = last;
    this.active = true;
  }

  public String getName(){
    return first + " " + last;
  }

  public int getId() {
    return id;
  }

  public Emp setId(int id) {
    this.id = id;
    return this;
  }

  public String getFirst() {
    return first;
  }

  public Emp setFirst(String first) {
    this.first = first;
    return this;
  }

  public String getLast() {
    return last;
  }

  public Emp setLast(String last) {
    this.last = last;
    return this;
  }

  public boolean isActive() {
    return active;
  }

  public Emp setActive(boolean active) {
    this.active = active;
    return this;
  }

  public Emp getManager() {
    return manager;
  }

  public Emp setManager(Emp manager) {
    this.manager = manager;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Emp emp = (Emp) o;

    return new EqualsBuilder()
        .append(id, emp.id)
        .append(active, emp.active)
        .append(first, emp.first)
        .append(last, emp.last)
        .append(manager, emp.manager)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(first)
        .append(last)
        .append(active)
        .append(manager)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("first", first)
        .append("last", last)
        .append("active", active)
        .append("manager", manager)
        .toString();
  }
}
