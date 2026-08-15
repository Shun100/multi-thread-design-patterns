package org.example.C02_Immutable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Immutableパターン
 * Immutableなオブジェクトは排他制御が不要というだけ（なのでデザインパターンというほどでもない）
 */
public class Main {
  public static void main(String[] args) {
    Person alice = new Person("Alice", "Alaska");

    try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
      executor.execute(new PrintPersonThread(alice));
      executor.execute(new PrintPersonThread(alice));
      executor.execute(new PrintPersonThread(alice));
    }

    // 直接Threadオブジェクトを操作する実装 (古い実装方法)
    // new Thread(new PrintPersonThread(alice)).start();
    // new Thread(new PrintPersonThread(alice)).start();
    // new Thread(new PrintPersonThread(alice)).start();
  }
}
