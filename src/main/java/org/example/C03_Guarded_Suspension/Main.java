package org.example.C03_Guarded_Suspension;

import org.example.C03_Guarded_Suspension.queue.BlockingRequestQueue;
import org.example.C03_Guarded_Suspension.queue.RequestQueue;
import org.example.C03_Guarded_Suspension.queue.VanillaRequestQueue;

// 片方の処理が終わるまでもう片方が待機する
public class Main {
  public static void main(String[] args) {
    // RequestQueue requestQueue = new VanillaRequestQueue();
    RequestQueue requestQueue = new BlockingRequestQueue();

    new ClientThread(requestQueue, "Alice", 3141592L).start();
    new ServerThread(requestQueue, "Bob", 653897L).start();
  }
}
