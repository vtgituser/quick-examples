package com.vt;

import static java.lang.Thread.sleep;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LongWork {
  private Map<String, Boolean> results = new ConcurrentHashMap<>();

  public void method1() {
    update();
  }

  private void update() {
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
    results.put(Thread.currentThread().getStackTrace()[2].getMethodName(), true);
  }

  public void method2() {
    update();
  }

  public void method3() {
    update();
  }

  public void method4() {
    update();
  }

  public void method5() {
    update();
  }

  public void method6() {
    update();
  }

  public Map<String, Boolean> getResults() {
    return results;
  }
}
