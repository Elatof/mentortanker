package com.korbiak.mentorship.multithreading.task4;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class PoolExecutor {

    public void execute() throws InterruptedException {
        BlockingObjectPool blockingPool = new BlockingObjectPool(3);

        Thread thread1 = new Thread(() -> {
            while (true) {
                blockingPool.get();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    //need only for see blocking
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("InterruptedException:{}", e.getMessage());
                    Thread.currentThread().interrupt();
                }
                blockingPool.put(new Random().nextInt(100));
            }
        });

        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();

        Thread.sleep(15000);
    }
}
