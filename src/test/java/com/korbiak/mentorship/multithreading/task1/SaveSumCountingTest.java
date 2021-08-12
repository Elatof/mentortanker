package com.korbiak.mentorship.multithreading.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

class SaveSumCountingTest {

    private static final SaveSumCounting sumCounting = new SaveSumCounting();

    @Test
    void executeTest() {
        sumCounting.execute();
    }

}
