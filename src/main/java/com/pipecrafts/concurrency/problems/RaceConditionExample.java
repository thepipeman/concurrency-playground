package com.pipecrafts.concurrency.problems;

public class RaceConditionExample {

  public static void main(String[] args) {
    Counter counter = new Counter();
//    Thread thread1 = new Thread(getRunnable(counter, "Thread1 count: "));
//    Thread thread2 = new Thread(getRunnable(counter, "Thread2 count: "));
    Thread thread1 = new Thread(getRunnable(counter, "Thread1 count: "));
    Thread thread2 = new Thread(getReaderRunnable(counter));

    thread1.start();
    thread2.start();
  }

  private static Runnable getRunnable(Counter counter, String message) {
    return () -> {
      for (int i = 0; i < 1000000; i++) {
        counter.incAndget();
      }
      System.out.println(message + counter.get());
    };
  }

  private static Runnable getReaderRunnable(Counter counter) {
    return () -> {
      for (int i = 0; i < 5; i++) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("T2 count: " + counter.get());
      }
    };
  }

  public static class Counter {
    private long count = 0;

    public long incAndget() {
      this.count++;
      return this.count;
    }

    public long get() {
      return this.count;
    }
  }
}
