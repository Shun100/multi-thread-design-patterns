package org.example.c11_thread_specific_storage;

class UserContext {
  /*
   * スレッドごとのユーザ情報を保持する
   *
   * ## スレッドローカルについて
   * スレッドごとに独立したデータを保持するためのクラス
   * 通常、複数のスレッドが同じ変数 (static変数) を共有すると、データの競合が発生する可能性がある。
   * しかし、`ThreadLocal`を使用すると、同じ変数にアクセスしていても、スレッドごとに異なる値を保持できる。
   */
  private static final ThreadLocal<String> CURRENT_USER = new ThreadLocal<>();

  /**
   * ユーザ情報取得
   * @return currentUser
   */
  static String currentUser() {
    return CURRENT_USER.get();
  }

  /**
   * ユーザ情報を設定して処理を実行する
   * <p>
   *   処理の流れ
   *   1. `CURRENT_USER.set(user)`で現在のユーザ情報を設定する。
   *   2. `action.run()`指定された処理を実行する。
   *   3. finallyで`CURRENT_USER.remove()`を実行する。
   * </p>
   * @param user - ユーザ
   * @param action - 処理
   */
  static void runAs(String user, Runnable action) {
    CURRENT_USER.set(user);
    try {
      action.run();
    } finally {
      CURRENT_USER.remove(); // 処理に失敗したとしてもユーザ情報は削除される
    }
  }
}
