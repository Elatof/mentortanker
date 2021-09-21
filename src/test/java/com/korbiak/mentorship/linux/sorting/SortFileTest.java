package com.korbiak.mentorship.linux.sorting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Slf4j
class SortFileTest {
    private final String SOURCE_PATH = "src/main/resources/linux/numbers";
    private final String TARGET_PATH = "src/main/resources/linux/sortedNumbers";

    private final SortFile sortFile = new SortFile(SOURCE_PATH);

    @Test
    void sortTest() {
        long startTime = System.nanoTime();
        Assertions.assertDoesNotThrow(() -> sortFile.sortFile(TARGET_PATH));
        long endTime = System.nanoTime();

        log.info("Duration sortTest: " + (endTime - startTime) + " milliseconds : " + new Date());
    }
}
