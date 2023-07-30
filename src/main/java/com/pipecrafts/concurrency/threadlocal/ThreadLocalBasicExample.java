package com.pipecrafts.concurrency.threadlocal;

public class ThreadLocalBasicExample {

  public static void main(String[] args) {

//    basicExample();
    inheritable();
  }

  private static void basicExample() {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    Thread t1 = new Thread(() -> {
      threadLocal.set("Thread 1");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      String value = threadLocal.get();
      System.out.println(value);
    });


    Thread t2 = new Thread(() -> {
      threadLocal.set("Thread 2");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      String value = threadLocal.get();
      System.out.println(value);
    });

    t1.start();
    t2.start();
  }

  private static void inheritable() {
    final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    Thread t1 = new Thread(() -> {
      System.out.println("=====  T1 ======");
      threadLocal.set("T1 - TL");
      inheritableThreadLocal.set("T1 - ITL");

      System.out.println(threadLocal.get());
      System.out.println(inheritableThreadLocal.get());

      final Thread tc = new Thread(() -> {
        System.out.println("=========== CT ======");
        System.out.println(threadLocal.get());
        System.out.println(inheritableThreadLocal.get());
      });

      tc.start();
    });

    t1.start();
  }
}
