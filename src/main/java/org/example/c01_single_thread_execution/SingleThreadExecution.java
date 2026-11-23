package org.example.c01_single_thread_execution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Single Thread Executionパターン
 * 複数のスレッドから共有オブジェクトにアクセスする場合に、同時に実行できるスレッドを1つに制限する。
 * 要するに、スレッドセーフにするということ。
 */
public class SingleThreadExecution {
  private final Gate gate = new Gate();

  public void runDemo() throws InterruptedException {
    System.out.println("C01 Single Thread Execution");
    try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
      for (int i = 0; i < 6; i++) {
        int n = i;
        executor.submit(() -> gate.pass("user-" + n, "address-" + n));
      }
    }
    System.out.println("\n");
  }
}
