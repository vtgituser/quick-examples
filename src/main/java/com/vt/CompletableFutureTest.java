package com.vt;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.CompletableFuture.runAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
  public static void main(String[] args) throws Exception {
    LongWork longWork = new LongWork();
    long start = currentTimeMillis();
    /*longWork.method1();
    longWork.method2();
    longWork.method3();
    longWork.method4();
    longWork.method5();
    longWork.method6();
    System.out.println(longWork.getResults());
    System.out.println(currentTimeMillis() - start);
    start = currentTimeMillis();*/
    List<CompletableFuture> futures = new ArrayList<>();
    futures.add(runAsync(longWork::method1));
    futures.add(runAsync(longWork::method2));
    futures.add(runAsync(longWork::method3));
    futures.add(runAsync(longWork::method4));
    futures.add(runAsync(longWork::method5));
    futures.add(runAsync(longWork::method6));
    CompletableFuture<Void> all =
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[6]));
    all.get();
    System.out.println(longWork.getResults());
    System.out.println(currentTimeMillis() - start);
  }
}
