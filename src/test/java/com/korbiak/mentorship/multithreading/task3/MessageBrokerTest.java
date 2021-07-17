package com.korbiak.mentorship.multithreading.task3;

import org.junit.jupiter.api.Test;

public class MessageBrokerTest {

    private final MessageBroker broker = new MessageBroker();

    @Test
    public void executeTest() throws InterruptedException {
        broker.execute();
    }
}
