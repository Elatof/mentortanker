package com.korbiak.mentorship.java8.aggregator.duplicates;

import com.korbiak.mentorship.java8.Aggregator;
import com.korbiak.mentorship.java8.aggregator.TimeReporter;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public abstract class JavaAggregatorDuplicatesTest {

    @Parameterized.Parameter(0)
    public long limit;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public List<String> expected;

    private Aggregator aggregator;

    public JavaAggregatorDuplicatesTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{3, asList("wwwoooo", "a", "BA", "wwwoOoo", "ba"), asList("BA", "WWWOOOO")});
        data.add(new Object[]{3, asList("all", "arguments", "a", "map", "wwwwwww", "map", "arguments", "all"), asList("ALL", "MAP", "ARGUMENTS")});
        data.add(new Object[]{2, asList("dog", "word", "intellij", "a", "b", "dog", "word", "intellij"), asList("DOG", "WORD")});
        data.add(new Object[]{3, Collections.emptyList(), Collections.emptyList()});
        data.add(new Object[]{3, asList("www"), Collections.emptyList()});
        return data;
    }

    @Test
    public void test() {
        long startTime = System.nanoTime();
        List<String> actual = aggregator.getDuplicates(words, limit);
        long endTime = System.nanoTime();
        assertEquals(expected, actual);
        TimeReporter.appendTimeReport("Duration " + aggregator.getClass() + ", input size: " + words.size() + " : " + (endTime - startTime) + " milliseconds : " + new Date());
    }
}