package org.example.c10_two_phase_termination;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

class BackgroundService implements AutoCloseable {
  /*
   * 終了要求管理フラグ
   * AtomicBooleanは、スレッドセーフなboolean型
   */
  private final AtomicBoolean shutdownRequested = new AtomicBoolean();

  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  /**
   * 非同期処理開始
   */
  void start() {
    executor.execute(() -> {
      try {
        // 終了要求を受け取るまで処理を繰り返す
        while (!shutdownRequested.get() && !Thread.currentThread().isInterrupted()) {
          System.out.println("BackgroundService working...");
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } finally {
        doCleanUp();
        System.out.println("BackgroundService stopped");
      }
    });
  }

  /**
   * 後片付け
   * <p>
   *   例えば以下のような処理を行う。
   *   - ファイルを閉じる
   *   - データベース接続を解放する
   *   - バッファをフラッシュする
   *   - 保持しているリソースを解放する
   * </p>
   */
  private void doCleanUp() {
    System.out.println("BackgroundService cleanup...");
  }

  /**
   * スレッド終了要求
   */
  void shutdown() {
    shutdownRequested.set(true);
    executor.shutdown();
  }

  /**
   * スレッド終了待機
   * <p>
   *   - 引数で受け取った時間だけ、スレッドの終了を待つ。
   *   - 待機時間経過しても終了していなければ、`shutdownNow()`で割り込みを要求する。
   *   - 引数で受け取った時間だけ、更にスレッドの終了を待つ。
   *   - スレッドの終了結果を返す。
   * </p>
   * @param timeout - タイムアウト時間
   * @return 終了結果
   * @throws InterruptedException
   */
  boolean awaitTermination(Duration timeout) throws InterruptedException {
    boolean terminated = executor.awaitTermination(timeout.toMillis(), TimeUnit.MILLISECONDS);
    if (!terminated) {
      executor.shutdownNow();
      terminated = executor.awaitTermination(timeout.toMillis(), TimeUnit.MILLISECONDS);
    }
    return terminated;
  }

  @Override
  public void close() throws InterruptedException {
    shutdown();
    awaitTermination(Duration.ofSeconds(2));
  }
}
