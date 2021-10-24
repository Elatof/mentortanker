package com.korbiak.mentorship.io.task3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Slf4j
class FastFileMoverTest {
    private FastFileMover fileMover;

    @Test
    void commonTestFile1FastFileMoverStream() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverStream();
        testFile("testFile1");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverStream File1: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void commonTestFile2FastFileMoverStream() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverStream();
        testFile("testFile2");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverStream File2: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void commonTestFile1FastFileMoverWithBuffer() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverWithBuffer();
        testFile("testFile1");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverWithBuffer File1: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void commonTestFile2FastFileMoverWithBuffer() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverWithBuffer();
        testFile("testFile2");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverWithBuffer File2: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void commonTestFile1FastFileMoverWithChannel() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverWithChannel();
        testFile("testFile1");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverWithChannel File1: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void commonTestFile2FastFileMoverWithChannel() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverWithChannel();
        testFile("testFile2");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverWithChannel File2: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void commonTestFile1FastFileMoverWithNIO() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverWithNio2();
        testFile("testFile1");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverWithNio2 File1: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    @Test
    void commonTestFile2FastFileMoverWithNIO() {
        long startTime = System.nanoTime();
        fileMover = new FastFileMoverWithNio2();
        testFile("testFile2");
        long endTime = System.nanoTime();
        log.info("Duration FastFileMoverWithNio2 File2: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    private void testFile(String fileName) {
        for (int i = 0; i < 500; i++) {
            String FILES_FOLDER = "src/test/resources/io/task3/filesFolder%s/%s.txt";
            String folder1 = String.format(FILES_FOLDER, 1, fileName);
            String folder2 = String.format(FILES_FOLDER, 2, fileName);
            fileMover.moveFile(folder1, folder2);
            fileMover.moveFile(folder2, folder1);
            log.info("Copy count: {}", i + 1);
        }
    }
}
