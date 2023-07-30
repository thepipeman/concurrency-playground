package com.pipecrafts.concurrency.synchronizd;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SynchronizedExchanger {

  private Long value;

  public synchronized Long getValue() {
    return value;
  }

  public synchronized void setValue(Long aValue) {
    this.value = aValue;
  }

  public Long getValueAlt() {
    synchronized (this) {
      return this.value;
    }
  }

  public void setValueAlt(Long aValue) {
    synchronized (this) {
      this.value = aValue;
    }
  }

}
