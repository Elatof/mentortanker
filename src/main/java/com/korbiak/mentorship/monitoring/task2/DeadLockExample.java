package com.korbiak.mentorship.monitoring.task2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLockExample {
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public void execute() {
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                log.info("Thread 1: locked resource 1");
                try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                log.info("Thread 2: locked resource 2");
                try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (resource1) {
                    log.info("Thread 2: locked resource 1");
                }
            }
        });


        t1.start();
        t2.start();
    }
}
