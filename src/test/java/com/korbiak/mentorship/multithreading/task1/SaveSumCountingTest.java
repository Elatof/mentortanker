package com.korbiak.mentorship.multithreading.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

public class SaveSumCountingTest {

    private static final SaveSumCounting sumCounting = new SaveSumCounting();

    @Test
    public void executeTest() {
        sumCounting.execute();
    }

}
