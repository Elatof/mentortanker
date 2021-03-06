package com.korbiak.mentorship.java8.aggregator.frequency;

import com.korbiak.mentorship.java8.Aggregator;
import com.korbiak.mentorship.java8.aggregator.TimeReporter;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public abstract class JavaAggregatorFrequencyTest {

    @Parameterized.Parameter(0)
    public long limit;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public List<Pair<String, Long>> expected;

    private Aggregator aggregator;

    public JavaAggregatorFrequencyTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{2, asList("f", "c", "b", "b", "b", "c"), asList(new Pair<>("b", 3L), new Pair<>("c", 2L))});
        data.add(new Object[]{2, asList("f", "f"), asList(new Pair<>("f", 2L))});
        data.add(new Object[]{2, asList("f"), asList(new Pair<>("f", 1L))});
        data.add(new Object[]{2, asList("a", "b", "b", "a"), asList(new Pair<>("a", 2L), new Pair<>("b", 2L))});
        data.add(new Object[]{2, Collections.emptyList(), Collections.emptyList()});
        return data;
    }

    @Test
    public void test() {
        long startTime = System.nanoTime();
        List<Pair<String, Long>> actual = aggregator.getMostFrequentWords(words, limit);
        long endTime = System.nanoTime();
        assertEquals(expected, actual);
        TimeReporter.appendTimeReport("Duration " + aggregator.getClass() + ", input size: " + words.size() + " : " + (endTime - startTime) + " milliseconds : " + new Date());
    }
}