package com.korbiak.mentorship.multithreading.task3;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Queue;
import java.util.Random;

@Slf4j
public class PCQueue {

    private final Queue<Integer> queue;
    private static final int BOUND = 10;
    private static final int CAPACITY = 1;
    private final String key;

    public PCQueue(Map<String, Queue<Integer>> queueMap, String key) {
        this.queue = queueMap.get(key);
        this.key = key;
    }

    public void produce(){
        while (true) {
            synchronized (this) {
                if (queue.size() == CAPACITY) {
                    try {
                        this.wait();
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                    }
                }
                Integer value = new Random().nextInt(BOUND);
                log.info("{} : producer produced-{}", key, value);
                queue.add(value);
                this.notifyAll();
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        }
        log.info("{} : producer stopped", key);
    }

    public void consume()  {
        while (true) {
            synchronized (this) {
                if (queue.isEmpty()) {
                    try {
                        this.wait();
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                    }
                }
                int val = queue.poll();
                log.info("{} : Consumer consumed-{}", key, val);
                this.notifyAll();
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        }
        log.info("{} : consumer stopped", key);
    }
}

