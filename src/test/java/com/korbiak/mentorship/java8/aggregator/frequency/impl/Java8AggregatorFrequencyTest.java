package com.korbiak.mentorship.java8.aggregator.frequency.impl;

import com.korbiak.mentorship.java8.Java8Aggregator;
import com.korbiak.mentorship.java8.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8AggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java8AggregatorFrequencyTest() {
        super(new Java8Aggregator());
    }
}

