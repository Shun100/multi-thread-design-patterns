package org.example.c12_active_object;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * 実行要求を表現するクラス
 * このクラスは次の情報を保持する。
 * - 計算に使用する引数
 * - 計算結果を返すための`CompletableFuture`
 * そして、`execute()`が呼び出されると、Servantに計算を委譲する。
 * 計算が完了したら、`Future`に結果を設定する。
 * つまり、メソッド呼び出しそのものをオブジェクトと表示している。
 */
@RequiredArgsConstructor
public class AddRequest implements MethodRequest {
  private final int left;
  private final int right;
  private final CompletableFuture<Integer> future;

  @Override
  public void execute(CalculatorServant servant) {
    try {
      int result = servant.add(left, right);
      future.complete(result);
    } catch (RuntimeException e) {
      future.completeExceptionally(e);
    }
  }
}
