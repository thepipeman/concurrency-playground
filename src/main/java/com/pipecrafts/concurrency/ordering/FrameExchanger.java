package com.pipecrafts.concurrency.ordering;

import java.awt.Frame;

public class FrameExchanger {

  // written and read directly from them main memory.
  private long framesStoredCount = 0;
  private long framesTakenCount = 0;
  private volatile boolean hasNewFrame = false;
  private Frame frame = null;

  // called by Frame producing thread
  public void storeFrame(Frame frame) {
    this.frame = frame;
    this.framesStoredCount++;
    this.hasNewFrame = true;
  }

  // called by Frame drawing thread
  public Frame takeFrame() {
    while (!hasNewFrame) {
      //busy wait until new frame arrives
    }

    Frame newFrame = this.frame;
    this.framesTakenCount++;
    this.hasNewFrame = false;
    return newFrame;
  }
}
