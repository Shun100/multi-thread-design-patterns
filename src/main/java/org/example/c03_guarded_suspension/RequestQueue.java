package org.example.c03_guarded_suspension;

import java.util.ArrayDeque;
import java.util.Queue;

class RequestQueue {
  private final Queue<String> queue = new ArrayDeque<>();

  /**
   * 要素追加
   * <p>
   *   要素を追加し、待機中のスレッドに通知する。
   * </p>
   * @param request - リクエストメッセージ
   */
  synchronized void put(String request) {
    queue.add(request);
    notifyAll();
  }

  /**
   * 要素取得
   * <p>
   *   要素が取得できるまで呼び元を待機させる。
   * </p>
   * @return message
   * @throws InterruptedException
   */
  synchronized String take() throws InterruptedException {
    // 解放条件は必ずwhileで確認する
    while(queue.isEmpty()) {
      wait(); // wait()を呼び出すとロックは解除される。そのため、デッドロックに陥ることは無い。
    }
    return queue.remove();
  }
}
