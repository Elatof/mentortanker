package com.korbiak.mentorship.multithreading.task2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Deadlock {

    private final List<Integer> targetList = new ArrayList<>();
    private final Object monitor = new Object();

    private static final int UPPERBOUND = 10;

    public void execute() throws InterruptedException {
        Thread sourceThread = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    monitor.notifyAll();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        log.error("InterruptedException in source:{}", e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                    int number = new Random().nextInt(UPPERBOUND);
                    log.info("Random number:{}", number);
                    targetList.add(number);
                }
            }
        });

        Thread sumThread = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    log.info("Sum: {}", targetList.stream().reduce(Integer::sum).orElse(0));
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        log.error("InterruptedException in sum:{}", e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        Thread squareThread = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    int squareSum = 0;
                    for (Integer integer : targetList) {
                        squareSum += integer * integer;
                    }
                    double sqrt = Math.sqrt(squareSum);
                    log.info("Sqrt of squareSum: {}", sqrt);
                    monitor.notifyAll();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        log.error("InterruptedException in square:{}", e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        sourceThread.start();
        sumThread.start();
        squareThread.start();

        sourceThread.join();
        sumThread.join();
        squareThread.join();
    }
}
