package org.example.c08_worker_thread;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements AutoCloseable {
  private final ExecutorService executor;

  Worker(int workerCount) {
    this.executor = Executors.newFixedThreadPool(workerCount);
  }

  void execute(Runnable job) {
    executor.execute(job);
  }

  @Override
  public void close() throws InterruptedException {
    executor.shutdown();
    if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
      executor.shutdownNow();
    }
  }
}
