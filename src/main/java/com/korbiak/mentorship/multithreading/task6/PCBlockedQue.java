package com.korbiak.mentorship.multithreading.task6;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Slf4j
public class PCBlockedQue extends PC {

    private final BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(getCapacity());
    private static final int BOUND = 10;

    protected PCBlockedQue(int capacity) {
        super(capacity);
    }

    @Override
    public void produce() throws InterruptedException {
        int count = 0;
        setStartTime(System.nanoTime());
        while (true) {
            Integer value = new Random().nextInt(BOUND);
            log.info("PCBlockedQue : producer produced-{}", value);
            queue.put(value);
            count++;
            if (Thread.currentThread().isInterrupted()) {
                log.info("PCBlockedQue : producer stopped");
                break;
            }
        }
        setCount(count);
        setEndTime(System.nanoTime());
    }

    @Override
    public void consume() throws InterruptedException {
        while (true) {
            int val = queue.take();
            log.info("PCBlockedQue : Consumer consumed-{}", val);
            if (Thread.currentThread().isInterrupted()) {
                log.info("PCBlockedQue : consumer stopped");
                break;
            }
        }
    }
}
