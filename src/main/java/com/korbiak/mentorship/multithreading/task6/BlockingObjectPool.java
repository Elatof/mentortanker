package com.korbiak.mentorship.multithreading.task6;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class BlockingObjectPool<T> {

    private final Queue<T> pool;
    private final int size;

    public BlockingObjectPool(int size) {
        this.size = size;
        this.pool = new PriorityQueue<>();
    }

    public synchronized T get() {
        if (pool.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error("InterruptedException:{}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        T object = pool.poll();
        log.info("Get object: {}", object);
        if (pool.size() == size - 1) {
            this.notifyAll();
        }
        return object;
    }

    public synchronized void put(T object) {
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
