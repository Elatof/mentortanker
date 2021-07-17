package com.korbiak.mentorship.multithreading.task2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Deadlock {

    private final List<Integer> targetList = new ArrayList<>();
    private final Object monitor = new Object();

    private static final int UPPERBOUND = 100;

    public void execute() throws InterruptedException {
        System.out.println("start");
        Thread sourceThread = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    monitor.notifyAll();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int number = new Random().nextInt(UPPERBOUND);
                    log.info("Random number:" + number);
                    targetList.add(number);
                }
            }
        });

        Thread sumThread = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    log.info("Sum: " + targetList.stream().reduce(Integer::sum).orElse(0));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread squareThread = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    log.info("Square: " + targetList.stream().reduce(0, (n1, n2) -> n1 * n1 + n2 * n2));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    monitor.notifyAll();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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
