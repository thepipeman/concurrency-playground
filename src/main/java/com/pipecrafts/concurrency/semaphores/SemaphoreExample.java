package com.pipecrafts.concurrency.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SemaphoreExample {


  public static void main(String[] args) {
    final var runner = new SemaphoreExample();
//    runner.blockLoginOnMaxSlots();
    runner.shouldLoginOnLogout();
  }

  private void blockLoginOnMaxSlots() {
    final int slots = 5;
    ExecutorService executorService = Executors.newFixedThreadPool(slots);
    LoginQueueSemaphore loginQueueSemaphore = new LoginQueueSemaphore(slots);
    IntStream.range(0, slots)
      .forEach(user -> executorService.execute(loginQueueSemaphore::tryLogin));
    executorService.shutdown();

    System.out.println(loginQueueSemaphore.availableSlots());
    System.out.println(loginQueueSemaphore.tryLogin());
  }

  private void shouldLoginOnLogout() {
    final int slots = 5;
    ExecutorService executorService = Executors.newFixedThreadPool(slots);
    LoginQueueSemaphore loginQueueSemaphore = new LoginQueueSemaphore(slots);
    IntStream.range(0, slots)
      .forEach(user -> executorService.execute(loginQueueSemaphore::tryLogin));
    executorService.shutdown();

    System.out.println(loginQueueSemaphore.availableSlots());
    System.out.println(loginQueueSemaphore.tryLogin());

    loginQueueSemaphore.logout();
    System.out.println(loginQueueSemaphore.availableSlots());
    System.out.println(loginQueueSemaphore.tryLogin());

    System.out.println(loginQueueSemaphore.availableSlots());
    System.out.println(loginQueueSemaphore.tryLogin());
  }

}
