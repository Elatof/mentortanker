package com.korbiak.mentorship.multithreading.task4;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class BlockingObjectPool {

    private final Queue<Object> pool;
    private final int size;

    public BlockingObjectPool(int size) {
        this.size = size;
        this.pool = new PriorityQueue<>();
    }

    public synchronized Object get() {
        if (pool.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error("InterruptedException:{}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        Object object = pool.poll();
        log.info("Get object: {}", object);
        if (pool.size() == size - 1) {
            this.notifyAll();
        }
        return object;
    }

    public synchronized void put(Object object) {
        if (pool.size() == size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error("InterruptedException:{}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        log.info("Put object: {}", object);
        pool.add(object);
        if (pool.size() == 1) {
            this.notifyAll();
        }
    }

}
