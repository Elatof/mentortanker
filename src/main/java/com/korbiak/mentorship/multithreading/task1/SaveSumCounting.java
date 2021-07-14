package com.korbiak.mentorship.multithreading.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SaveSumCounting {

    private static final int COUNT = 10000;
    private final Map<Integer, Integer> targetMap = new HashMap<>();
    private final Object monitor = new Object();

    public void execute() {
        log.info("Start execute");
        Thread putThread = new Thread(new PutElements());
        Thread sumThread = new Thread(new CountElements());

        putThread.start();
        sumThread.start();

        try {
            putThread.join();
            putThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Stop execute");
    }

    private class PutElements implements Runnable {

        @Override
        public void run() {
            log.info("Start 'PutElements' thread: {}", Thread.currentThread().getName());
            for (int i = 0; i < COUNT; i++) {
                synchronized (monitor) {
                    try { monitor.wait(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                    log.info("Put: {} , {}", i, i);
                    targetMap.put(i, i);
                    monitor.notify();
                }
            }
            log.info("End 'PutElements' thread: {}", Thread.currentThread().getName());
        }
    }

    private class CountElements implements Runnable {

        @Override
        public void run() {
            log.info("Start 'CountElements' thread: {}", Thread.currentThread().getName());
            while (true) {
                synchronized (monitor) {
                    monitor.notify();
                    try { monitor.wait(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                    if (targetMap.size() == COUNT) {
                        log.info("End 'CountElements' thread: {}", Thread.currentThread().getName());
                        break;
                    }
                    int sum = targetMap.values().stream().reduce(Integer::sum).orElse(0);
                    log.info("Sum: {}", sum);
                }
            }
        }
    }
}
