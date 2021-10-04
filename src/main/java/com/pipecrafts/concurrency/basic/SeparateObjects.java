package com.pipecrafts.concurrency.basic;

public class SeparateObjects {


  public static void main(String[] args) {


    DummyObject dummyObject = new DummyObject();

    // since both runnables reference the same DummyObject, they are access the same exact object.
    // This object is saved in the heap rather than the local thread stack.
    Runnable r1 = new MyRunnable(dummyObject);
    Runnable r2 = new MyRunnable(dummyObject);

    Thread t1 = new Thread(r1, "T1");
    Thread t2 = new Thread(r2, "T2");

    t1.start();
    t2.start();
  }
}
