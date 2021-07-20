package com.korbiak.mentorship.multithreading.task6;

import org.junit.jupiter.api.Test;

public class BrokerTest {

    private final Broker broker = new Broker();

    @Test
    public void executeTest() {
        broker.execute();
    }
}
