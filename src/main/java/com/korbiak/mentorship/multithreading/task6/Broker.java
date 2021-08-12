package com.korbiak.mentorship.multithreading.task6;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

@Slf4j
public class Broker {

    public void execute() {
        BlockingObjectPool<Integer> integerQueue = new BlockingObjectPool<>(3);
        Queue<Integer> queue = new PriorityBlockingQueue<>(3);

        log.info("BlockingObjectPool: ");
        Consumer consumerPool = new Consumer(null, integerQueue);
        Producer producerPool = new Producer(null, integerQueue);
        Thread consumer = new Thread(consumerPool);
        Thread producer = new Thread(producerPool);
        consumer.start();
        producer.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("InterruptedException:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        consumer.interrupt();
        producer.interrupt();

        log.info("PriorityBlockingQueue: ");
        Consumer consumerQu = new Consumer(queue, null);
        Producer producerQu = new Producer(queue, null);
        Thread consumer2 = new Thread(consumerQu);
        Thread producer2 = new Thread(producerQu);
        consumer2.start();
        producer2.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("InterruptedException:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        consumer2.interrupt();
        producer2.interrupt();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("BlockingObjectPool: time report");
            printTimeResult(consumerPool, producerPool);
            System.out.println("PriorityBlockingQueue: time report");
            printTimeResult(consumerQu, producerQu);
        }));
    }

    private void printTimeResult(Consumer consumer, Producer producer) {
        int countCons = consumer.getCount();
        long timeCons = (consumer.getEndTime() - consumer.getStartTime()) / 1000000000;
        long performance = countCons / timeCons;
        System.out.println("Consumer: time - " + timeCons + " s., count - " + countCons + ", perf - " + performance);

        int countPro = producer.getCount();
        long timePro = (producer.getEndTime() - producer.getStartTime()) / 1000000000;
        long performancePro = countPro / timePro;
        System.out.println("Producer: time - " + timePro + " s., count - " + countPro + ", perf - " + performancePro);
    }
}
