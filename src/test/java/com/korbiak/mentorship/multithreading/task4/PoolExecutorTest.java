package com.korbiak.mentorship.multithreading.task4;

import org.junit.jupiter.api.Test;

public class PoolExecutorTest {

    private final PoolExecutor executor = new PoolExecutor();

    @Test
    public void executorTest() throws InterruptedException {
        executor.execute();
    }
}
