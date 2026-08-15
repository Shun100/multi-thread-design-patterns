package org.example.C01_Single_Thread_Execution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main (String[] args) {
    BankAccount account = new BankAccount();

    try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
      executor.execute(() -> account.unsafeWithdraw(700));
      executor.execute(() -> account.unsafeWithdraw(500));
    }

    // Threadオブジェクトを直接操作する実装 (古い実装方法)
    // Thread t1 = new Thread(() -> account.unsafeWithdraw(700), "Thread-1");
    // Thread t2 = new Thread(() -> account.unsafeWithdraw(500), "Thread-2");
    // Thread t1 = new Thread(() -> account.safeWithdraw(700), "Thread-1");
    // Thread t2 = new Thread(() -> account.safeWithdraw(500), "Thread-2");
    // t1.start();
    // t2.start();
  }
}
