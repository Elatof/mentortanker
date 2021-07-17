package com.korbiak.mentorship.multithreading.task2;

import org.junit.jupiter.api.Test;

public class DeadLockTest {

    private final Deadlock deadLock = new Deadlock();

    @Test
    public void executeTest() throws InterruptedException {
        deadLock.execute();
    }
}
