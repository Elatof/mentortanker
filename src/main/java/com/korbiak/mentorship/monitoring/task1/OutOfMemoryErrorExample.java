package com.korbiak.mentorship.monitoring.task1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class OutOfMemoryErrorExample {

    //java -XX:+PrintFlagsFinal -version | findstr /i "HeapSize PermSize ThreadStackSize"
    public void execute() {
        int size = 1000;
        Integer[] myArray = new Integer[0];
        try {
            for (int i = 0; i < 3; i++) {
                pressKey();
                log.info("Trying size: {}", size);
                myArray = new Integer[size];
                log.info("Created myArray with size: {} --> OK", size);
                size = size * 1000;
            }
            log.info("Final myArray with size: {}", myArray.length);
        } catch (OutOfMemoryError oome) {
            log.info("Created myArray with size: {} --> ERROR", size);
            log.error("Array size too large: {} --> ERROR {}", size, oome.getMessage());
        }
        pressKey();
    }

    private void pressKey() {
        try {
            log.info("Press any key...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
