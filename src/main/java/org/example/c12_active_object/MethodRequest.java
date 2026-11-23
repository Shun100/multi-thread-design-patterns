package org.example.c12_active_object;

/**
 * 実行要求を表現するためのインタフェース
 */
interface MethodRequest {
  // 処理実行
  void execute(CalculatorServant servant);
}
