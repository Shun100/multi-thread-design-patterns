package org.example.c12_active_object;

class Scheduler implements AutoCloseable {
  private final ActivationQueue queue = new ActivationQueue();
  private final CalculatorServant servant = new CalculatorServant();
  private final MethodRequest STOP = s -> {};
  private final Thread worker;
  private boolean accepting = true;

  Scheduler() {
    worker = new Thread(this::dispatch, "ActiveObject-Worker");
    worker.start();
  }

  private void dispatch() {
    try {
      while (true) {
        MethodRequest request = queue.take();
        if (request == STOP) {
          break;
        }
        request.execute(servant);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  synchronized void enqueue(MethodRequest request) {
    if (!accepting) {
      throw new IllegalStateException("Scheduler is shutting down.");
    }
    queue.put(request);
  }

  @Override
  public void close() throws InterruptedException {
    synchronized (this) {
      if (accepting) {
        accepting = false;
        queue.put(STOP);
      }
    }

    worker.join(2000);

    if (worker.isAlive()) {
      worker.interrupt();
      worker.join(2000);
    }
  }
}
