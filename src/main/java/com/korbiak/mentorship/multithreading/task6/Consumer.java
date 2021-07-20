package com.korbiak.mentorship.multithreading.task6;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
@Getter
public class Consumer implements Runnable {

    private final Queue<Integer> queue;
    private final BlockingObjectPool<Integer> pool;

    private int count = 0;
    private long startTime;
    private long endTime = 0L;
    @Override
    public void run() {
        startTime = System.nanoTime();
        while (true) {
            Integer value = null;
            if (Objects.nonNull(queue)) {
                value = queue.poll();
            }
            if (Objects.nonNull(pool)) {
                value = pool.get();
            }
            count++;
            log.info("Consume: value-{}", value);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.info("Consumer thread interrupted");
                break;
            }
        }
        endTime = System.nanoTime();
    }
}
