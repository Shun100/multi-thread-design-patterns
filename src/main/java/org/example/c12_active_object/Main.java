package org.example.c12_active_object;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * # Active Objectパターン
 * メソッドの呼び出しと、そのメソッドの実行を分離することで、非同期処理を実現するデザインパターン。
 * メソッドを呼び出したスレッドとは別のスレッドで、そのメソッドの処理を実行する。
 *
 * ## 活用例
 * 例えば、時間のかかる処理を実行する場合を考える。
 * 通常のメソッド呼び出しでは、処理が完了するまで呼び出し元のスレッドは待機する。
 * しかし、Active Objectパターンを利用すると、処理を依頼した後、呼び出し元は結果を待たずに別の処理を続けられる。
 */
public class Main {
  public static void main(String[] args) {
    try (Calculator calculator = new Calculator()) {
      CompletableFuture<Integer> result = calculator.add(10, 20);
      System.out.println("Active Object result: " + result.get());
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e.getMessage());
    }
  }
}
