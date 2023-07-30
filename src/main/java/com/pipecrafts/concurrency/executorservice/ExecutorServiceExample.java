package com.pipecrafts.concurrency.executorservice;

import java.util.*;
import java.util.concurrent.*;

public class ExecutorServiceExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    simpleExecution();
    submitSample();
    submitCallabe();
    invokeAnyExample();
    invokeAllExample();
  }

  private static void simpleExecution() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(buildRunnable());
    executorService.shutdown();
  }

  private static void submitSample() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future future = executorService.submit(buildRunnable());
    future.get();
    executorService.shutdown();
  }

  private static void submitCallabe() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> future = executorService.submit(() -> {
      System.out.println(Thread.currentThread().getName() + ": async task");
      return "Random result";
    });
    System.out.println("Result: " + future.get());
    executorService.shutdown();
  }

  private static void invokeAnyExample() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    Set<Callable<String>> callables = new HashSet<>();

    callables.add(() -> "Task 1");
    callables.add(() -> "Task 2");
    callables.add(() -> "Task 3");

    String result = executorService.invokeAny(callables);

    System.out.println("result = " + result);

    executorService.shutdown();
  }

  private static void invokeAllExample() throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Set<Callable<String>> callables = new HashSet<>();

    callables.add(() -> "Task 1");
    callables.add(() -> "Task 2");
    callables.add(() -> "Task 3");

    List<Future<String>> results = executorService.invokeAll(callables);
    for (Future<String> result : results) {
      System.out.println("Future.get = " + result.get());
    }
    executorService.shutdown();

  }

  private static Runnable buildRunnable() {
    return () -> System.out.println(Thread.currentThread().getName() + ": async task");
  }
}
