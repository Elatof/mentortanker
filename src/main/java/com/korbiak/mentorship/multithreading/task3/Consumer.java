package com.korbiak.mentorship.multithreading.task3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;

@Slf4j
@AllArgsConstructor
public class Consumer implements Runnable {

    private final Map<String, Queue<Integer>> queueMap;
    private final String topic;

    @Override
    public void run() {
        while (true) {
            Integer value = queueMap.get(topic).poll();
            if (Objects.nonNull(value)) {
                log.info("Consume: topic-{} value-{}", topic, value);
            }
            try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
