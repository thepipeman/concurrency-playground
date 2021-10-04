package com.pipecrafts.concurrency.basic;

public class SharedObjects {

  public static void main(String[] args) {
    DummyObject dummyObject = new DummyObject();
    Runnable r1 = new MyRunnable(dummyObject);

    Thread t1 = new Thread(r1, "T1");
    Thread t2 = new Thread(r1, "T2");

    t1.start();
    t2.start();
  }
}
