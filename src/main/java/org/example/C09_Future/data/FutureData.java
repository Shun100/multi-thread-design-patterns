package org.example.C09_Future.data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureData implements Data {
  private final Future<RealData> future;

  public FutureData(Future<RealData> future) {
    this.future = future;
  }

  @Override
  public String getContent() {
    try {
      return future.get().getContent();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new IllegalStateException("Interrupted while waiting for the result", e);
    } catch (ExecutionException e) {
      throw new IllegalStateException("Failed to create the result", e.getCause());
    }
  }
}
