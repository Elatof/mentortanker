package com.korbiak.mentorship.multithreading.task3;

import java.util.*;

public class MessageBroker {

    public void execute() throws InterruptedException {
        //init
        Map<String, Queue<Integer>> map = new HashMap<>(2);
        map.put("First", new PriorityQueue<>());
        map.put("Second", new PriorityQueue<>());

        //start produce
        Thread producer1 = new Thread(new Producer(map, "First"));
        Thread producer2 = new Thread(new Producer(map, "Second"));

        //end produce
        Thread consumer1 = new Thread(new Consumer(map, "First"));
        Thread consumer2 = new Thread(new Consumer(map, "Second"));

        producer1.setDaemon(true);
        producer2.setDaemon(true);
        consumer1.setDaemon(true);
        consumer2.setDaemon(true);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        //waiting for end
        Thread.sleep(15000);
    }

}
