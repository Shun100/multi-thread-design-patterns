package org.example.C05_Producer_Consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Producer-Consumerパターン
 * データの生産者と消費者の処理速度のすれを埋める
 */
public class Main {
  public static void main(String[] args) {
    Table table = new Table(3); // 3: buffer size

    try (ExecutorService executor = Executors.newFixedThreadPool(6)) {
      executor.execute(() -> new CakeProducer(table, 31415).run());
      executor.execute(() -> new CakeProducer(table, 92653).run());
      executor.execute(() -> new CakeProducer(table, 58979).run());
      executor.execute(() -> new CakeProducer(table, 32384).run());
      executor.execute(() -> new CakeProducer(table, 62643).run());
      executor.execute(() -> new CakeConsumer(table, 38327).run());
    }
  }
}
