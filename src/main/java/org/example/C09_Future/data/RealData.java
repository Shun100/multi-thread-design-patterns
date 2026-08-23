package org.example.C09_Future.data;

public class RealData implements Data {
  private final String content;

  public RealData(int count, char c) {
    System.out.println("  making RealData(" + count + ", " + c + ") BEGIN");
    StringBuilder contentBuilder = new StringBuilder(count);
    for (int i = 0; i < count; i++) {
      contentBuilder.append(c);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new IllegalStateException("Interrupted while creating RealData", e);
      }
    }
    System.out.println("  making RealData(" + count + ", " + c + ") END");
    this.content = contentBuilder.toString();
  }

  @Override
  public String getContent() {
    return content;
  }
}
