package org.example.C03_Guarded_Suspension.queue;

import org.example.C03_Guarded_Suspension.Request;

import java.util.LinkedList;
import java.util.Queue;

// Blocking Queue未使用版
public class VanillaRequestQueue implements RequestQueue {
  private final Queue<Request> queue = new LinkedList<>();

  public synchronized Request getRequest() {
    while(queue.isEmpty()) {
      try {
        wait(); // 基礎知識: wait()を呼ぶとロックが解除される
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    return queue.remove();
  }

  public synchronized void putRequest(Request request) {
    queue.offer(request);
    notifyAll();
  }
}
