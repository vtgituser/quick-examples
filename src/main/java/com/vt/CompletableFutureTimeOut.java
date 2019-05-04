package com.vt;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class CompletableFutureTimeOut {

  public static void main(String[] args) {
  /*  final CompletableFuture<Response> responseFuture = within(
        asyncCode(), Duration.ofSeconds(1));
    responseFuture
        .thenAccept(this::send)
        .exceptionally(throwable -> {
          System.out.println("Unrecoverable error" + throwable);
          return null;
        });*/
  }

  public static <T> CompletableFuture<T> within(CompletableFuture<T> future, Duration duration) {
    final CompletableFuture<T> timeout = failAfter(duration);
    return future.applyToEither(timeout, Function.identity());
  }

  public static <T> CompletableFuture<T> failAfter(Duration duration) {
    final CompletableFuture<T> promise = new CompletableFuture<>();
    scheduler.schedule(() -> {
      final TimeoutException ex = new TimeoutException("Timeout after " + duration);
      return promise.completeExceptionally(ex);
    }, duration.toMillis(), MILLISECONDS);
    return promise;
  }

  private static final ScheduledExecutorService scheduler =
      Executors.newScheduledThreadPool(
          1, r -> new Thread(r, ""));


  /*,
      new ThreadFactoryBuilder()
              .setDaemon(true)
              .setNameFormat("failAfter-%d")
              .build()*/
}
