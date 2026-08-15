package org.example.C03_Guarded_Suspension.queue;

import org.example.C03_Guarded_Suspension.Request;

public interface RequestQueue {
  Request getRequest() throws InterruptedException;
  void putRequest(Request request) throws InterruptedException;
}
