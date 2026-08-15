package org.example.C05_Producer_Consumer;

import java.util.Random;

public class CakeConsumer {
  private final Random random;
  private final Table table;

  public CakeConsumer(Table table, long seed) {
    this.table = table;
    this.random = new Random(seed);
  }

  public void run() {
    try {
      while (true) {
        String cake = table.take(); // Queueからデータを取得
        Thread.sleep(random.nextInt(1000));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }
}
