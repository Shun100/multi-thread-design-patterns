# multi-thread-design-patterns
マルチスレッドのデザインパターン学習用

## 各デザインパターンの実装方法

| No | デザインパターン | 実装方法 |
| --- | --- | --- |
| 1 | Single Threaded Execution | `synchronized` |
| 2 | Immutable | `final`クラス・フィールド |
| 3 | Guarded Suspension | `wait()` / `nottifyAll()` |
| 4 | Balking | 条件判定と同期 |
| 5 | Producer-Consumer | `BlockingQueue` |
| 6 | Read-Write Lock | `ReentrantReadWriteLock` |
| 7 | Thread-Per-Message | `newVirtualThreadPerTaskExecutor()` |
| 8 | Worker Thread | `newFixedThreadPool()` |
| 9 | Future | `ExecutorService.submit()` / `Future` |
| 10 | Two-Phase Termination | `shutdown()` / `shutdownNow()` |
| 11 | Thread-Specific Storage | `ThreadLocal` |
| 12 | Active Object | `newSingleThreadExecutor()` / `CompletableFuture` |

## 実装方針
1. ExecutorServiceを基本とする
   - スレッドの直接生成・管理を避ける。
   - タスクの実行とライフサイクル管理を分離する。
   - `try-with-resource`によるリソース管理を活用する。
2. モダンなJava APIを使用する
   - Producer-Consumerには`BlockingQueue`を使用。
   - Thread-PerMessageにはjava 21の仮想スレッドを使用。
   - Active Objectには`CompletableFuture`を使用。
3. スレッドセーフティを考慮する
   - `wait()`後の条件再確認には`while`を使用。
   - ロックは`try-finally`で確実に解放。
   - `ThreadLocal`はタスク終了時に`remove()`する。