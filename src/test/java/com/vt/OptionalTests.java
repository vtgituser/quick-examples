package com.vt;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;

public class OptionalTests {

  @Test
  public void orElseTest() {
    assertEquals("Emp1", findEmployee(1));
  }

  Optional<String> getFromCache(int id) {
    System.out.println("search in cache with Id: " + id);
    return Optional.of("Emp1");
  }

  Optional<String> getFromDB(int id) {
    System.out.println("search in Database with Id: " + id);
    if (id == 1) return Optional.of("Emp2");
    else return Optional.empty();
  }

  public String findEmployee(int id) {
    return getFromCache(id)
        .orElse(
            getFromDB(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id" + id)));
  }
}
