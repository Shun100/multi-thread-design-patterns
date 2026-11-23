package org.example.c09_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * # Futureパターン
 * 時間のかかる処理を非同期で実行し、その処理結果を後から取得するためのデザインパターン。
 * 処理の完了を待たずに先に進み、処理結果が必要になったタイミングで結果を受け取る。
 */
public class Main {
  public static void main(String[] args) {
    try (FutureResult task = new FutureResult()) {
     // 非同期処理を開始
      Future<Integer> future = task.calculate();

      // 計算処理の完了を待たずに後続処理を実行
      System.out.println("Other processing 1...");
      System.out.println("Other processing 2...");
      System.out.println("Other processing 3...");

      // 必要になったタイミングで結果を取得
      System.out.println("Future result: " + future.get());

    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e.getMessage());
    }
  }
}
