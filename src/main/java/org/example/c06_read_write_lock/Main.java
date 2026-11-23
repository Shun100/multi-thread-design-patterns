package org.example.c06_read_write_lock;

/**
 * # Read-Write Lockパターン
 * 読み取りロックと書き込みロックを使い分け、複数スレッドが共有データにアクセスする際の競合を制御するためのデザインパターン。
 * 読み取り処理が多く、書き込み処理が少ないシステムで効果を発揮する。
 *
 * ## ロックの種類
 * - 読み取りロック
 *  - 読み取りは複数スレッドで同時実行可能。一方で、書き込みは不可。
 * - 書き込みロック
 *  - 読み取り不可。書き込みは1スレッドのみ可。
 */
public class Main {
  public static void main(String[] args) {
    SafeDictionary dictionary = new SafeDictionary();
    dictionary.put("hello", "こんにちは");
    System.out.println(dictionary.get("hello"));
  }
}
