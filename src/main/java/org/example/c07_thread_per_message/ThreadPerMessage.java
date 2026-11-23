package org.example.c07_thread_per_message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ThreadPerMessage implements AutoCloseable {
  private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

  void send(String message) {
    executor.execute(() -> System.out.println(message + "handled by " + Thread.currentThread()));
  }

  @Override
  public void close() throws InterruptedException {
    executor.shutdown();
    if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
      executor.shutdownNow();
    }
  }
}
