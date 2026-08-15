package org.example.C03_Guarded_Suspension.queue;

import org.example.C03_Guarded_Suspension.Request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingRequestQueue implements RequestQueue{
  private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

  public void putRequest(Request request) throws InterruptedException {
    queue.put(request); // 基礎知識: put()メソッドは排他制御が組み込まれているのでsynchronized不要
  }

  public Request getRequest() throws InterruptedException {
    return queue.take(); // 基礎知識: take()メソッドは排他制御が組み込まれているのでsynchronized不要
  }
}
