package org.example.c12_active_object;

/**
 * 実際の業務処理を担当するクラス
 * 他のクラスを用いて、このクラスの処理を非同期実行する。
 */
public class CalculatorServant {
  /**
   * 業務処理
   * @param left - 足し算する数1
   * @param right - 足し算する数2
   * @return 足し算結果
   */
  int add(int left, int right) {
    System.out.println("Calculating: " + left + " + " + right);

    // 時間のかかる処理をシミュレート
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new IllegalStateException("Calculation interrupted", e);
    }

    return left + right;
  }
}
