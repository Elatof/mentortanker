package com.korbiak.mentorship.multithreading.task1;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ConcurrentModificationException;
import java.util.Date;

class SumCountingTest {

    private final static String REPORT_PATH = "src/test/java/com/korbiak/mentorship/multithreading/task1/timeReport";
    private static final SumCounting sumCounting = new SumCounting();

    @Test
    void executeHashMapTest() {
        long startTime = System.nanoTime();
        Assertions.assertThrows(ConcurrentModificationException.class, sumCounting::executeHashMap);
        long endTime = System.nanoTime();

        appendTimeReport("Duration executeHashMapTest: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void executeConcurrentHashMapTest() {
        long startTime = System.nanoTime();
        sumCounting.executeConcurrentHashMap();
        long endTime = System.nanoTime();

        appendTimeReport("Duration executeConcurrentHashMapTest: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void executeSynchronizedMapTest() {
        long startTime = System.nanoTime();
        Assertions.assertThrows(ConcurrentModificationException.class, sumCounting::executeSynchronizedMap);
        long endTime = System.nanoTime();

        appendTimeReport("Duration executeSynchronizedMapTest: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void executeCustomThreadSafeMapTest() {
        long startTime = System.nanoTime();
        sumCounting.executeCustomThreadSafeMap();
        long endTime = System.nanoTime();

        appendTimeReport("Duration executeCustomThreadSafeMapTest: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void executeCustomSynchronizedThreadSafeMapTest() {
        long startTime = System.nanoTime();
        sumCounting.executeCustomSynchronizedThreadSafeMap();
        long endTime = System.nanoTime();

        appendTimeReport("Duration executeCustomSynchronizedThreadSafeMapTest: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @SneakyThrows
    private void appendTimeReport(String message) {
        System.out.println(message);
        Files.write(Paths.get(REPORT_PATH), (message + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }
}
