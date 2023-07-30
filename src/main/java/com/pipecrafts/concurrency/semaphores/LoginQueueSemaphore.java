package com.pipecrafts.concurrency.semaphores;

import java.util.concurrent.Semaphore;

public class LoginQueueSemaphore {

  private Semaphore semaphore;

  public LoginQueueSemaphore(int slotLimit) {
    semaphore = new Semaphore(slotLimit);
  }

  boolean tryLogin() {
    return semaphore.tryAcquire();
  }

  void logout() {
    semaphore.release();
  }

  int availableSlots() {
    return semaphore.availablePermits();
  }
}
