package com.korbiak.mentorship.java8.aggregator;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TimeReporter {

    private final static String REPORT_PATH = "src/test/java/com/korbiak/mentorship/java8/aggregator/timeReport.txt";

    private TimeReporter() {
    }

    @SneakyThrows
    public static void appendTimeReport(String message) {
        System.out.println(message);
        Files.write(Paths.get(REPORT_PATH), (message + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }
}
