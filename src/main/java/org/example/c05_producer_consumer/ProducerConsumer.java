package org.example.c05_producer_consumer;

import java.util.concurrent.*;

class ProducerConsumer implements AutoCloseable {
  private final int POISON_PILL = -1;
  // BlockingQueueは内部でスレッドセーフな排他制御とスレッド間の待機・通知を実装しているため、操作する際にsynchronizedは不要。
  private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);
  private final ExecutorService executor = Executors.newFixedThreadPool(2);
  private Future<?> producer;
  private Future<?> consumer;

  private ProducerConsumer() {}

  static void runDemo() throws InterruptedException, ExecutionException {
    try (ProducerConsumer pc = new ProducerConsumer()) {
      pc.start();
      pc.await();
    }
  }

  void start() {
    producer = executor.submit(() -> {
      try {
        for (int i = 1; i <= 5; i++) {
          queue.put(i);
          System.out.println("Produced" + i);
        }
        queue.put(POISON_PILL);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });

    consumer = executor.submit(() -> {
      try {
        while (true) {
          int item = queue.take();
          if (item == POISON_PILL) break;
          System.out.println("Consumed: " + item);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });
  }

  void await() throws InterruptedException, ExecutionException {
    producer.get();
    consumer.get();
  }

  @Override
  public void close() throws InterruptedException {
    executor.shutdown();
    if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
      executor.shutdownNow();
    }
  }
}
