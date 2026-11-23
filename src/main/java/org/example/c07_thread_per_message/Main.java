package org.example.c07_thread_per_message;

/**
 * Thread-Per-Messageパターン
 * メッセージを受け取る度に、新しいスレッドを生成して処理を実行するデザインパターン。
 * このパターンを利用すると、ある処理が時間のかかる処理であっても、メッセージを受け取った側は処理の完了を待たずに次のメッセージを受け取れる。
 */
public class Main {
  public static void main(String[] args) {
    try (ThreadPerMessage dispatcher = new ThreadPerMessage()) {
      for (int i = 0; i < 3; i++) {
        dispatcher.send("message" + i);
      }
    } catch (InterruptedException e) {
      System.err.println(e.getMessage());
    }
  }
}
