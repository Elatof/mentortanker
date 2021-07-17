package com.korbiak.mentorship.multithreading.task4;

import java.util.Random;

public class PoolExecutor {

    public void execute() throws InterruptedException {
        BlockingObjectPool blockingPool = new BlockingObjectPool(3);

        Thread thread1 = new Thread(() -> { while (true) { blockingPool.get(); } });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
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
