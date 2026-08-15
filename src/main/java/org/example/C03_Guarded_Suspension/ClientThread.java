package org.example.C03_Guarded_Suspension;

import org.example.C03_Guarded_Suspension.queue.RequestQueue;
import org.example.C03_Guarded_Suspension.queue.VanillaRequestQueue;

import java.util.Random;

// QueueのSupplier
public class ClientThread extends Thread {
  private final Random random;
  private final RequestQueue requestQueue;

  public ClientThread(RequestQueue requestQueue, String name, long seed) {
    super(name);
    this.requestQueue = requestQueue;
    this.random = new Random(seed);
  }

  public void run() {
    for (int i = 0; i < 10000; i++) {
      Request request= new Request("No." + i);
      System.out.println(getName() + " requests " + request);
      try {
        requestQueue.putRequest(request);
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
