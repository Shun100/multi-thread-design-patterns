package org.example.c05_producer_consumer;

import java.util.concurrent.ExecutionException;

/**
 * # Producer-Consumerパターン
 * データを生成するスレッド (Producer) と、データを消費するスレッド (Consumer) を分離し、キューを介してデータを受け渡す仕組み。
 *
 * ## 使いどころ
 * 例えば、Producerが1秒間に10件のデータを生成し、Consumerが1秒間に2件しか処理できないとする。
 * ProducerとConsumerを直接連携させると、処理速度の違いを調整する必要がある。
 * そこで、キューを導入する。
 * - Producerはデータをキューに追加し続ける。
 * - Consumerは自分の処理速度に合わせてキューからデータを取り出す。
 * - Consumerが遅くても、キューがデータを一時的に保持してくれる。
 */
public class Main {
  public static void main(String[] args) {
    try {
      ProducerConsumer.runDemo();
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e.getMessage());
    }
  }
}
