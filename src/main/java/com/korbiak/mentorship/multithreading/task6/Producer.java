package com.korbiak.mentorship.multithreading.task6;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Getter
public class Producer implements Runnable {

    private final Queue<Integer> queue;
    private final BlockingObjectPool<Integer> pool;
    private static final int BOUND = 100;

    private int count = 0;
    private long startTime;
    private long endTime = 0L;

    @Override
    public void run() {
        startTime = System.nanoTime();
        while (true) {
            Integer value = new Random().nextInt(BOUND);
            if (Objects.nonNull(queue)) {
                queue.add(value);
            }
            if (Objects.nonNull(pool)) {
                pool.put(value);
            }
            count++;
            log.info("Produce: value-{}", value);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.info("Producer thread interrupted");
                break;
            }
        }
        endTime = System.nanoTime();
    }
}
