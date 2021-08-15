package com.korbiak.mentorship.multithreading.task6;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@Slf4j
public class PCLinkedList extends PC {

    private final Queue<Integer> queue = new LinkedList<>();
    private static final int BOUND = 10;

    public PCLinkedList(int capacity) {
        super(capacity);
    }

    @Override
    public void produce() throws InterruptedException {
        int count = 0;
        setStartTime(System.nanoTime());
        while (true) {
            synchronized (this) {
                if (queue.size() == getCapacity())
                    this.wait();
                Integer value = new Random().nextInt(BOUND);
                log.info("PCLinkedList : producer produced-{}", value);
                queue.add(value);
                count++;
                this.notifyAll();
                if (Thread.currentThread().isInterrupted()) {
                    log.info("PCLinkedList : producer stopped");
                    break;
                }
            }
        }
        setCount(count);
        setEndTime(System.nanoTime());
    }

    @Override
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (queue.isEmpty())
                    this.wait();
                int val = queue.poll();
                log.info("PCLinkedList : Consumer consumed-{}", val);
                this.notifyAll();
                if (Thread.currentThread().isInterrupted()) {
                    log.info("PCLinkedList : consumer stopped");
                    break;
                }
            }
        }
    }
}

