package org.example.C04_Balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {
  private final String filename;
  private String content;
  private boolean changed;

  public Data(String filename, String content) {
    this.filename = filename;
    this.content = content;
    this.changed = true;
  }

  /**
   * データ上書き
   * @param newContent - 新しいデータ
   */
  public synchronized void change(String newContent) {
    content = newContent;
    changed = true;
  }

  /**
   * ファイルの内容が変更されていたらファイルに書き込む
   * @throws IOException - 書き込み失敗
   */
  public synchronized void save() throws IOException {
    if (!changed) { return; }

    doSave();
    changed = false;
  }

  /**
   * ファイル書き込み
   * @throws IOException - 書き込み失敗
   */
  private void doSave() throws IOException {
    System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
    Writer writer = new FileWriter(filename);
    writer.write(content);
    writer.close();
  }
}
