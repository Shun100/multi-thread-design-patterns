package org.example.c11_thread_specific_storage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * # Thread-Specific Storage
 * スレッドごとに独立したデータを保持するためのデザインパターン。
 * 複数のスレッドが、同じクラスや仕組みを利用しながら、それぞれ専用のデータを保持する。
 *
 * ## 活用例
 * 例えば、Webアプリケーションで複数ユーザから同時にリクエストを受け付けたとする。
 * このとき、各リクエストを処理するスレッドが、それぞれ独自のユーザ情報やデータベース接続情報を保持したいことがある。
 *
 * ## 基本的な考え方
 * 複数のスレッドで同じオブジェクトを共有すると、データの競合が発生する可能性がある。
 * そこで、スレッドごとに独立したデータ領域を用意することで、この問題を解決する。
 */
public class Main {
  public static void main(String[] args) {
    try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
      Future<?> first = executor.submit(() -> UserContext.runAs(
          "Alice",
          () -> System.out.println("Current user: " + UserContext.currentUser()))
      );

      Future<?> second = executor.submit(() -> UserContext.runAs(
          "Bob",
          () -> System.out.println("Current user: " + UserContext.currentUser()))
      );

      // マルチスレッドなので、どちらの結果が先に出力されるかは不定
      first.get();
      second.get();

    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e.getMessage());
    }
  }
}
