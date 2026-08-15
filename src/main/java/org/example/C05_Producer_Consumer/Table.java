package org.example.C05_Producer_Consumer;

// Queue
public class Table {
  private final String[] buffer;
  private int tail = 0; // 次にデータを追加する位置
  private int head = 0; // 次にデータを取り出す位置
  private int size = 0; // 現在キューに入っているデータ数

  public Table(int capacity) {
    this.buffer = new String[capacity];
  }

  public synchronized void put(String cake) throws InterruptedException {
    while(isFull()) { wait(); } // notify()されるとwait()を抜ける

    buffer[tail] = cake;
    tail = nextIndex(tail);
    size++;

    System.out.println(Thread.currentThread().getName() + " puts " + cake);

    notifyAll();
  }

  public synchronized String take() throws InterruptedException {
    while(isEmpty()) { wait(); } // notify()されるとwait()を抜ける

    String cake = buffer[head];
    buffer[head] = null; // 取り出した要素を解放
    head = nextIndex(head);
    size--;

    System.out.println(Thread.currentThread().getName() + " takes " + cake);

    notifyAll();

    return cake;
  }

  private boolean isFull() { return size == buffer.length; }

  private boolean isEmpty() { return size == 0; }

  private int nextIndex(int index) {
    return (index + 1) % buffer.length;
  }
}
