package org.example.c04_balking;

class Configuration {
  private String value = "default";
  private boolean changed = false;

  synchronized void update(String newValue) {
    value = newValue;
    changed = true;
  }

  synchronized void saveIfNeeded() {
    // 条件を満たしていない場合は処理を終了する。(待たない)
    if (!changed) {
      System.out.println("Balking: no changed; skip saving");
      return;
    }
    // この例では保存処理を同期区間内で表現している。
    System.out.println("Saving configuration: " + value);
    changed = false;
  }
}
