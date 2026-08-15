package org.example.C05_Read_Write_Lock.data;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class DataBuffer {
  private final Deque<Data> buffer = new LinkedList<>();
  private final LockHander lockHandler;

  public Data read() throws InterruptedException {
    lockHandler.readLock();
    try {
      return buffer.pollFirst();
    } finally {
      lock.readUnlock();
    }
  }

  public void write(Data data) throws InterruptedException {
    lock.writeLock();
    try {
      buffer.add(data);
    } finally {
      lock.writeUnlock();
    }
  }
}
