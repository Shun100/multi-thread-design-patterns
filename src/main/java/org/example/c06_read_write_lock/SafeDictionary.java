package org.example.c06_read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SafeDictionary {
  private final Map<String, String> map = new HashMap<>();
  private final ReadWriteLock lock = new ReentrantReadWriteLock();

  /**
   * 読み取り
   * <p>
   *   読み取りロックの正しい実装になっている。
   *   - Read Lockを取得する。
   *   - `map.get()`でデータを読み込む。
   *   - 読み込みが終了したらロックを解放する。
   *   - Read Lockなので、複数のスレッドが同時に本メソッドを実行できる。
   *    - `ReadWriteLock`インタフェースが管理する。
   *    - `ReadWriteLock`インタフェースが排他制御を管理するため、`synchronized`は不要。
   * </p>
   * @param key
   * @return
   */
  String get(String key) {
    lock.readLock().lock(); // 読み取りロック
    try {
      return map.get(key);
    } finally {
      lock.readLock().unlock(); // ロック開放
    }
  }

  /**
   * 書き込み
   * <p>
   *   書き込みロックの正しい実装になっている。
   *   - Write Lockを取得する。
   *   - `map.put()`でデータを書き込む。
   *   - 書き込みが終了したらロックを開放する。
   *   - Write Lockなので、ほかの読み込み・書き込み処理が実行中の場合は、それらが終わるまで待機する。
   *    - `ReadWriteLock`インタフェースが排他制御を管理するため、`synchronized`は不要。
   * </p>
   * @param key
   * @param value
   */
  void put(String key, String value) {
    lock.writeLock().lock(); // 書き込みロック
    try {
      map.put(key, value);
    } finally {
      lock.writeLock().unlock(); // ロック開放
    }
  }
}
