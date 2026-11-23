package org.example.c03_guarded_suspension;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Guarded Suspensionパターン
 * 条件が満たされるまでスレッドを待機させ、条件が成立したら処理を再開させる。
 * 構成要素
 *  - Guard: ガード条件。処理を実行してよいか判定する条件
 *  - Suspension: 待機。ガード条件を満たしていない場合にスレッドを待機させる。
 *  - Notification: 通知。ガード条件を満たしたら待機中のスレッドを起こす。
 */

public class GuardedSuspension {
  private final RequestQueue requestQueue = new RequestQueue();

  public void runDemo() throws InterruptedException, ExecutionException {
    try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
      Future<String> consumer = executor.submit(requestQueue::take);
      requestQueue.put("request-1");
      System.out.println("Guarded Suspension: " + consumer.get());
    }
  }
}
