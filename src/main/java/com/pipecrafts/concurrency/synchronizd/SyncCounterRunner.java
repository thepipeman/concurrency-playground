package com.pipecrafts.concurrency.synchronizd;

public class SyncCounterRunner {

  public static void main(String[] args) {
    SyncCounter counter = new SyncCounter();

    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        counter.increment();
      }
      System.out.println(counter.getCount());
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        counter.increment();
      }
      System.out.println(counter.getCount());
    });

    t1.start();
    t2.start();
  }
}
