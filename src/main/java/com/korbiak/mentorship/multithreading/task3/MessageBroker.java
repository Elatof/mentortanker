package com.korbiak.mentorship.multithreading.task3;

import com.korbiak.mentorship.multithreading.task4.BlockingObjectPool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MessageBroker {

    private static final String FIRST = "First";
    private static final String SECOND = "Second";

    public void execute() throws InterruptedException {
        //init

        Map<String, Queue<Integer>> map = new HashMap<>(2);
        map.put(FIRST, new LinkedList<>());
        map.put(SECOND, new LinkedList<>());

        PCQueue pcQueue1 = new PCQueue(map, FIRST);
        PCQueue pcQueue2 = new PCQueue(map, SECOND);
        //start produce
        Thread producer1 = new Thread(pcQueue1::produce);
        Thread producer2 = new Thread(pcQueue2::produce);

        Thread consumer1 = new Thread(pcQueue1::consume);
        Thread consumer2 = new Thread(pcQueue2::consume);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        //waiting for end
        Thread.sleep(20);

        producer1.interrupt();
        producer2.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();
    }
}
