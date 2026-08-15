package org.example.C03_Guarded_Suspension;

import org.example.C03_Guarded_Suspension.queue.RequestQueue;
import org.example.C03_Guarded_Suspension.queue.VanillaRequestQueue;

import java.util.Random;

// Queue Consumer
public class ServerThread extends Thread {
  private final Random random;
  private final RequestQueue requestQueue;

  public ServerThread(RequestQueue requestQueue, String name, long seed) {
    super(name);
    this.requestQueue = requestQueue;
    this.random = new Random(seed);
  }

  public void run() {
    for (int i = 0; i < 10000; i++) {
      try {
        Request request = requestQueue.getRequest();
        System.out.println(getName() + " handles " + request);
      } catch (InterruptedException e) {
        break;
      }

      try {
        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException e) {
        interrupt();
        break;
      }
    }
  }
}
