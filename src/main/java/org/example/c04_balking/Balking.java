package org.example.c04_balking;

/**
 * Balkingパターン
 * 条件を満たしていない場合、処理を待たずに即座に中断・終了する。
 */

public class Balking {
  private final Configuration configuration = new Configuration();

  public void runDemo() {
    configuration.update("production");
    configuration.saveIfNeeded();
    configuration.saveIfNeeded();
  }
}
