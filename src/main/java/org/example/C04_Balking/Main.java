package org.example.C04_Balking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Balkingパターン
 * Balk = 途中でやめる の意味
 * 途中で処理が不要になったらその時点で処理を中断してやめる
 * Guarded Suspensionパターンでは処理が実行可能になるまで待ち続けていたのに対して、
 * Balkingパターンではやめてしまうというのが大きな違い
 */
public class Main {
  public static void main(String[] args) {
    Data data = new Data("data.txt", "(empty)");
    try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
      executor.execute(() -> new AutoEditor(data).run());
      executor.execute(() -> new AutoSaver(data).run());
    }
  }
}
