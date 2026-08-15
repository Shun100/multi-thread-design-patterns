package org.example.C01_Single_Thread_Execution;

public class BankAccount {
  private int balance = 1000;

  public synchronized  void safeWithdraw(int amount) {
    withdraw(amount);
  }

  public void unsafeWithdraw(int amount) {
    withdraw(amount);
  }

  private void withdraw(int amount) {
    if (balance >= amount) {
      System.out.println(Thread.currentThread().getName() + " が　" + amount + "円引き出します");

      balance -= amount;

      System.out.println("残高: " + balance + "円");
    } else {
      System.out.println("残高不足");
    }
  }
}
