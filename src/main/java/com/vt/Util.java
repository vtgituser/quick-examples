package com.vt;

public class Util {
  /** Casts the receiver to type T (unsafe). T can be automatically inferred. */
  @SuppressWarnings("unchecked")
  public static <T> T cast(Object object) {
    return (T) object;
  }
}
