package org.example.C02_Immutable;

public record PrintPersonThread(Person person) implements Runnable {
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " prints " + person);
  }
}
