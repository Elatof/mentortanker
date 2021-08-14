package com.korbiak.mentorship.multithreading.task6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Broker {

    public void execute() {
        PC pc1 = new PCLinkedList(1);
        PC pc2 = new PCBlockedQue(1);

        log.info("PCLinkedList: ");
        Thread consumer = new Thread(() -> {
            try {
                pc1.produce();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                exception.printStackTrace();
            }
        });
        Thread producer = new Thread(() -> {
            try {
                pc1.consume();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                exception.printStackTrace();
            }
        });
        producer.start();
        consumer.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("InterruptedException:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        consumer.interrupt();
        producer.interrupt();

        log.info("PCBlockedQue: ");
        Thread consumer2 = new Thread(() -> {
            try {
                pc2.produce();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                exception.printStackTrace();
            }
        });
        Thread producer2 = new Thread(() -> {
            try {
                pc2.consume();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                exception.printStackTrace();
            }
        });
        producer2.start();
        consumer2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("InterruptedException:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        consumer2.interrupt();
        producer2.interrupt();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("PCLinkedList: time report");
            printTimeResult(pc1);
            System.out.println("PCBlockedQue: time report");
            printTimeResult(pc2);
        }));
    }

    private void printTimeResult(PC pc) {
        int countCons = pc.getCount();
        long timeCons = (pc.getEndTime() - pc.getStartTime()) / 1000000000;
        long performance = countCons / timeCons;
        System.out.println("Time - " + timeCons + " s., count - " + countCons + ", perf - " + performance);
    }
}
