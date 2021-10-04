package com.pipecrafts.concurrency.basic;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyRunnable implements Runnable {

  // if used for the same thread, this will be accessed by any thread t hat run the same instance
  // this is shared between the threads
  private int count = 0;
  private DummyObject dummyObject;

  public MyRunnable(DummyObject dummyObject) {
    this.dummyObject = dummyObject;
  }

  @Override
  public void run() {

    // local varaiable i
    // each thread will create its own copy of since it is a local variable
    // will be created in its own thread stack
    // local variables are never shared between threads
    for (int i = 0; i < 1_000_000; i++) {
      synchronized (this) {
        this.count++;
      }
    }

    System.out.println(Thread.currentThread().getName() + " : " + this.count);

  }
}
