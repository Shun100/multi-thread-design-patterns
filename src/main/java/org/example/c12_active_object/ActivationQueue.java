package org.example.c12_active_object;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 実行要求を保持するためのキュークラス
 * `LinkedBlockingQueue`を使用することで、複数のスレッドから要求を登録でき、要求を取り出す側はキューが空の場合に待機できる。
 */
class ActivationQueue {
  private final BlockingQueue<MethodRequest> queue = new LinkedBlockingQueue<>();

  void put(MethodRequest request) {
    queue.add(request);
  }

  MethodRequest take() throws InterruptedException {
    return queue.take();
  }
}
