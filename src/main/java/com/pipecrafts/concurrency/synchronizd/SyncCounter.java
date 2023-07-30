package com.pipecrafts.concurrency.synchronizd;

public class SyncCounter {

  private long count = 0;

  public synchronized void increment() {
    this.count++;
  }

  public synchronized long getCount() {
    return this.count;
  }
}