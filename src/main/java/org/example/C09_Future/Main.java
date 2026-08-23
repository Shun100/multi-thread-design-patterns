package org.example.C09_Future;

import org.example.C09_Future.data.Data;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("main BEGIN");
    System.out.println("main otherJob BEGIN");
    try (Host host = new Host()) {
      List<Data> dataList = List.of(
          host.request(10, 'A'),
          host.request(20, 'B'),
          host.request(30, 'C'));

      // getContent()は値が返って来るまで待ち続ける
      dataList.forEach(data -> System.out.println("data = " + data.getContent()));

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new IllegalStateException("Interrupted during otherJob", e);
      }
      System.out.println("main otherJob END");

      // 既に処理は完了済みなので値はすぐ取得できる
      dataList.forEach(data -> System.out.println("data = " + data.getContent()));
    }
  }
}
