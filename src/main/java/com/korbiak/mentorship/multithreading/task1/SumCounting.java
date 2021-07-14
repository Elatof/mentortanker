package com.korbiak.mentorship.multithreading.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SumCounting {

    private static final int COUNT = 10000;
    private Map<Integer, Integer> targetMap;
    private ConcurrentModificationException ex;

    public void executeHashMap() {
        targetMap = new HashMap<>();
        execute();
    }

    public void executeConcurrentHashMap() {
        targetMap = new ConcurrentHashMap<>();
        execute();
    }

    public void executeSynchronizedMap() {
        targetMap = Collections.synchronizedMap(new HashMap<>());
        execute();
    }

    public void executeCustomThreadSafeMap() {
        targetMap = new CustomThreadSafeMap<>();
        execute();
    }

    public void executeCustomSynchronizedThreadSafeMap() {
        targetMap = new CustomSynchronizedThreadSafeMap<>();
        execute();
    }

    private void execute() {
        log.info("Start execute");
        Thread putThread = new Thread(new PutElements());
        Thread sumThread = new Thread(new CountElements());

        putThread.start();
        sumThread.start();

        try {
            putThread.join();
            sumThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Objects.nonNull(ex)) {
            throw ex;
        }

        log.info("Stop execute");
    }

    private class PutElements implements Runnable {

        @Override
        public void run() {
            log.info("Start 'PutElements' thread: {}", Thread.currentThread().getName());
            for (int i = 0; i < COUNT; i++) {
                log.info("Put: {} , {}", i, i);
                targetMap.put(i, i);
            }
            log.info("End 'PutElements' thread: {}", Thread.currentThread().getName());
        }
    }

    private class CountElements implements Runnable {

        @Override
        public void run() {
            log.info("Start 'CountElements' thread: {}", Thread.currentThread().getName());
            while (true) {
                if (targetMap.size() == COUNT) {
                    log.info("End 'CountElements' thread: {}", Thread.currentThread().getName());
                    break;
                }
                try {
                    int sum = targetMap.values().stream().reduce(Integer::sum).orElse(0);
                    log.info("Sum: {}", sum);
                } catch (ConcurrentModificationException e) {
                    ex = e;
                }
            }
        }
    }
}
