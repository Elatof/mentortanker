package com.korbiak.mentorship.multithreading.task3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

@Slf4j
@AllArgsConstructor
public class Producer implements Runnable {

    private final Map<String, Queue<Integer>> queueMap;
    private final String topic;


    @Override
    public void run() {
        while (true) {
            Integer value = new Random().nextInt(100);
            queueMap.get(topic).add(value);
            log.info("Produce: topic-{} value-{}", topic, value);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("InterruptedException:{}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
