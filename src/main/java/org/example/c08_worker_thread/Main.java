package org.example.c08_worker_thread;

/**
 * Worker Threadパターン
 * 複数のスレッドをあらかじめ用意しておき、それらのスレッドにタスクを繰り返し割り当てて処理するデザインパターン。
 * 要するに、スレッドプール。
 */
public class Main {
  public static void main(String[] args) {
    try (Worker worker = new Worker(2)) {
      for (int i = 0; i < 5; i++) {
        int job = i;
        worker.execute(() -> System.out.println(
            "job" + job + "handled by " + Thread.currentThread().getName()
        ));
      }
    } catch (InterruptedException e) {
      System.err.println(e.getMessage());
    }
  }
}
