package org.example.C04_Balking;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.util.Random;

// 定期的に自動的にファイルを編集 -> 保存するスレッド (動作検証用)
@RequiredArgsConstructor
public class AutoEditor {
  private final Data data;
  private final Random random = new Random();

  public void run() {
    try {
      for (int i=0; true; i++) {
        data.change("No." + i);
        Thread.sleep(random.nextInt(1000)); // 適当な処理
        data.save();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
