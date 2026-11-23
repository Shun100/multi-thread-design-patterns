package org.example.c02_immutable;

/**
 * Immutableパターン
 * 一度生成したオブジェクトの状態を変更できないようにすることで、マルチスレッド環境でも安全にオブジェクトを共有できるようにする。
 */
public class Immutable {
  public void runDemo() {
    System.out.println("C02 Immutable");
    ImmutablePerson person = new ImmutablePerson("Alice", 20);
    System.out.println(person);
    System.out.println("\n");
  }
}
