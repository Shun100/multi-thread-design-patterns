package org.example.C05_Producer_Consumer;

import java.util.Random;

public class CakeProducer {
  private final Random random;
  private final Table table;
  private static int id = 0;

  public CakeProducer(Table table, long seed) {
    this.table = table;
    this.random = new Random(seed);
  }

  public void run() {
    try {
      while (true) {
        Thread.sleep(random.nextInt(1000));
        String cake = "[ Cake No." + nextId() + " by " + Thread.currentThread().getName() + " ] ";
        table.put(cake); // Queueにデータを追加
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  private static synchronized int nextId() {
    return id++;
  }
}
