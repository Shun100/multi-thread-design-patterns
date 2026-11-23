package org.example.c09_future;

import java.sql.Time;
import java.util.concurrent.*;

class FutureResult implements AutoCloseable {
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  /**
   * 計算処理
   * <p>
   *   何らかの時間がかかる処理。結果が取得できるまで時間がかかる。
   * </p>
   * @return future
   */
  Future<Integer> calculate() {
    Callable<Integer> calculation = () -> {
      TimeUnit.MILLISECONDS.sleep(100);
      return 21 * 2;
    };
    return executor.submit(calculation);
  }

  @Override
  public void close() throws InterruptedException {
    executor.shutdown();
    if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
      executor.shutdownNow();
    }
  }
}
