package com.vt;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletableFuturePerformance {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuturePerformance f = new CompletableFuturePerformance();
    BlockingQueue<Runnable> queue;
    queue = new ArrayBlockingQueue<>(1000);
    ThreadPoolExecutor pool =
        new ThreadPoolExecutor(
            10,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            queue,
            (r, executor) -> {
              try {
                if (executor != null && !executor.isShutdown()) {
                  executor.getQueue().put(r);
                }
              } catch (InterruptedException e) {
                System.out.println("Thread Interrupted " + e.getMessage());
              }
            });

    long start = System.currentTimeMillis();
    List<CompletableFuture> futures = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      final int j = i;
      /*      futures
      .add(CompletableFuture.supplyAsync(() -> f.task1(j)).thenAccept(f::task2));*/
      //      CompletableFuture.supplyAsync(() -> f.task1(j), pool).thenAccept(f::task2).get();
      futures.add(
          CompletableFuture.runAsync(() -> f.task1(j), pool)
              .handle(
                  (m, t) -> {
                    t.printStackTrace();
                    return m;
                  }));
    }
    System.out.println("queue capacity:" + queue.remainingCapacity());
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[1000])).get();
    System.out.println("time(ms) : " + (System.currentTimeMillis() - start));
  }

  public int task1(int i) {
    /*  if (i % 3 == 0) {
      throw new RuntimeException();
    }*/
    try {
      //      System.out.println("task1 " + i);
      sleep(1000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return i;
  }

  public void task2(int i) {
    try {
      System.out.println("task2 " + i);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
