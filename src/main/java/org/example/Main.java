package org.example;

import java.nio.charset.Charset;

public class Main {
  public static void main(String[] args) {
    System.out.println("こんにちは！");

    System.out.println(
      "file.encoding  = " + System.getProperty("file.encoding")
    );

    System.out.println(
      "native.encoding = " + System.getProperty("native.encoding")
    );

    System.out.println(
      "defaultCharset  = " + Charset.defaultCharset()
    );
  }
}