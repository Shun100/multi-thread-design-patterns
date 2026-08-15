package org.example.C04_Balking;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

// 定期的にデータの自動保存を行うスレッド
@RequiredArgsConstructor
public class AutoSaver {
  private final Data data;

  public void run() {
    try {
      while(true) {
        data.save();
        Thread.sleep(1000);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
