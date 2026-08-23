package org.example.C09_Future;

import org.example.C09_Future.data.Data;
import org.example.C09_Future.data.FutureData;
import org.example.C09_Future.data.RealData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Host implements AutoCloseable {
  private final ExecutorService executor = Executors.newFixedThreadPool(3);

  public Data request(int count, char c) {
    System.out.println("  request(" + count + ", " + c + ") BEGIN");

    FutureData future = new FutureData(executor.submit(() -> new RealData(count, c)));

    System.out.println("  request(" + count + ", " + c + ") END");

    return future;
  }

  @Override
  public void close() {
    executor.shutdown();
  }
}
