package com.pipecrafts.concurrency.synchronizd;

public class Reentrance {

  private int count = 0;

  public synchronized void increment() {
    this.count++;
  }

  public synchronized int incrementAndGet() {

    // this sync method will not block the thread that invoke this method.
    // a thread that already holds the lock is allowed to enter a new sync block which has the same lock.
    increment();
    return this.count;
  }
}
