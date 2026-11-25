# multi-thread-design-patterns

マルチスレッドのデザインパターン学習用

- C01 Single Thread Execution
  - 共有リソースに同時にアクセスできるスレッドを1つに制限する。
- C02 Immutable
  - 共有リソースの状態を変更できないようにする。
- C03 Guarded Suspension
  - 処理を実行するための条件が満たされるまで、スレッドを待機状態にする。
- C04 Balking
  - 処理を実行するための条件が満たされていなければ、スレッドを終了する。
- C05 Producer-Consumer
  - キューを通してデータのやり取りをすることで、ProducerとConsumerの処理速度の差を埋める。
- C06 Read-Write Lock
  - 共有リソースの読み取りと書き込みで異なるロックをかける。
    - 読み取りロック: 他スレッドも読み取り可能。書き込みは不可。
    - 書き込みロック: 他スレッドは読み取りも書き込みも不可。
- C07 Thread-Per Message
  - メッセージごとにスレッドを起動して並列処理する。
- C08 Worker Thread
  - スレッドを使いまわす。要するにスレッドプールのこと。
- C09 Future
  - 処理の完了を待たずに先に進み、処理結果が必要になったタイミングで結果を受け取る。
- C10 Tho-Phase Termination
  - スレッドに終了要求を送り、スレッド自身に後片付けをさせてから終了させることで、安全に終了させる。
- C11 Thread-Specific Storage
  - スレッドごとに独立したデータを保持する。
    - これだけだと当たり前にできることに聞こえるが、`ThreadLocal`を使うことで`static`変数もスレッドごとに異なる値を持てるようになる。
    - **まだ理解できていないので調査中**
- C12 Active Object
  - メソッドの呼び出しと、そのメソッドの実行を分離することで、非同期処理を実現する。
    - **まだ理解できていないので調査中**

## C01 Single Thread Execution

1. 概要

- 複数のスレッドから同じオブジェクトや共有リソースにアクセスする場合に、同時に実行できるスレッドを1つに制限する。

2. 目的

- データの不整合や競合状態を防ぎ、プログラムの整合を保つ。

3. 活用場面

- 銀行システム
  - 口座残高の更新
- 在庫管理
  - 商品の在庫数の増減
- チケット販売
  - 座席の重複予約防止
- ファイル操作
  - 共有ファイルへの書き込み
- キャッシュ管理
  - 共有キャッシュの更新

4. 実現方法

- `synchronized`や`Lock`

## C02 Immutable

1. 概要

- 一度生成したオブジェクトの状態を変更できないようにすることで、複数スレッドから安全に利用できるようにする。

2. 目的

- 複数スレッドから同じオブジェクトを安全に共有すること。
- オブジェクトの状態変更を禁止することで、データ競合を防止し、ロックによる排他制御を必要としないスレッドセーフな設計を実現する。

3. 利用場面

- ユーザ情報
  - ユーザID, 氏名, メールアドレス
- 設定情報
  - システム設定, 環境設定
- 商品情報
  - 商品ID, 商品名, 価格
- 日時情報
  - 日付, 時刻, タイムゾーン
- アプリケーションの状態
  - 初期化後に変更しない設定値

4. 実現方法

- `final`, `setter`を実装しない等

## C03 Guarded Suspension

1. 概要

- 処理を実行するための条件が満たされるまで、スレッドを待機状態にする。

2. 目的

- 条件が満たされていない状態での不適切な処理を防止する。
- 条件が成立するまでスレッドを待機させることで、CPUの無駄な消費を抑える。
- 複数スレッド間の処理タイミングを調整する。
- データの準備やリソースの確保など、処理の前提条件を安全に管理する。

3. 利用場面

- Producer-Consumer処理
  - キューにデータが追加されるまで消費側が待機する。
- データ受信処理
  - ネットワークからデータを受信するまで待機する。
- リソース管理
  - 利用可能なリソースが確保されるまで待機する。
- 初期化処理
  - 必要な初期化が完了するまで待機する。
- タスク管理
  - 実行対象のタスクが登録されるまで待機する。

4. 実現方法

- `wait()` + `notify()` / `notifyAll()`

## C04 Balking

1. 概要

- メソッドの実行時にあらかじめ定義された条件を確認し、条件が満たされていなければそのまま終了する。

2. 目的

- 処理を実行できない状態での無駄な処理や不適切な操作を防止し、システムの安全性と効率性を高める。
  - 実行条件を満たさない処理を防止する。
  - 不要なスレッドの待機を発生させない。
  - 同じ処理の重複実行を防止する。
  - オブジェクトの状態に応じて適切に処理を制御する。

3. 利用場面

- 初期化処理
  - 初期化済みの場合、再初期化を行わない。
- ファイル操作
  - ファイルが閉じられている場合、書き込みを拒否する。
- バックアップ処理
  - バックアップ実行中の場合、重複実行を防止する。
- バッチ処理
  - 既に実行済みの処理を再実行しない。
- 状態管理
  - オブジェクトが終了状態の場合、新しい処理を受け付けない。

4. 実現方法

- `synchronized` + フラグ変数

## C05 Producer Consumer

## C06 Read-Write Lock

## C07 Thread Per Message

## C08 Worker Thread

## C09 Future

## C10 Two Phase Termination

## C11 Thread Specific Storage

## C12 Active Object

## 各デザインパターンの実装方法

| No  | デザインパターン          | 実装方法                                          |
| --- | ------------------------- | ------------------------------------------------- |
| 1   | Single Threaded Execution | `synchronized`                                    |
| 2   | Immutable                 | `final`クラス・フィールド                         |
| 3   | Guarded Suspension        | `wait()` / `nottifyAll()`                         |
| 4   | Balking                   | 条件判定と同期                                    |
| 5   | Producer-Consumer         | `BlockingQueue`                                   |
| 6   | Read-Write Lock           | `ReentrantReadWriteLock`                          |
| 7   | Thread-Per-Message        | `newVirtualThreadPerTaskExecutor()`               |
| 8   | Worker Thread             | `newFixedThreadPool()`                            |
| 9   | Future                    | `ExecutorService.submit()` / `Future`             |
| 10  | Two-Phase Termination     | `shutdown()` / `shutdownNow()`                    |
| 11  | Thread-Specific Storage   | `ThreadLocal`                                     |
| 12  | Active Object             | `newSingleThreadExecutor()` / `CompletableFuture` |

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
