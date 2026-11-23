package org.example.c10_two_phase_termination;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * # Two-Phase Terminationパターン
 * スレッドを安全に終了させるためのデザインパターン。
 * 具体的には、スレッドに終了要求を送り、スレッド自身に後片付けをさせてから終了させる。
 *
 * ## 基本的な考え方
 * Two-Phase Terminationは、名前の通り2つのフェーズで構成されている。
 * - Phase 1: スレッドに終了要求を通知する。
 * - Phase 2: スレッドが後片付けを行い、自分自身を終了させる。
 */
public class Main {
  public static void main(String[] args) {
    try (BackgroundService service = new BackgroundService()) {
      service.start();
      TimeUnit.MILLISECONDS.sleep(100);

      // Phase 1: 終了要求を通知する
      service.shutdown();

      // Phase 2: スレッドが後片付けを行い、自分自身を終了させる

      // 2秒以内に終了しなければ割り込みを要求し、更に2秒間、終了を待つ
      service.awaitTermination(Duration.ofSeconds(2));
    } catch (InterruptedException e) {
      System.err.println(e.getMessage());
    }
  }
}
