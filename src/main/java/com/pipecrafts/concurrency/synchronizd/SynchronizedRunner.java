package com.pipecrafts.concurrency.synchronizd;

public class SynchronizedRunner {

  public static void main(String[] args) {
    SynchronizedExchanger exchanger = new SynchronizedExchanger(1L);
    Runnable r1 = () -> {
      for (int i = 0; i < 1000; i++) {
        exchanger.setValue((long) i);
      }
    };

    Runnable r2 = () -> {
      for (int i = 0; i < 1000; i++) {
        System.out.println(exchanger.getValue());
      }
    };

    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);

    t1.start();
    t2.start();
  }
}
