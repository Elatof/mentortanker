package com.korbiak.mentorship.java8.aggregator.frequency.impl;

import com.korbiak.mentorship.java8.Java7Aggregator;
import com.korbiak.mentorship.java8.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7AggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java7AggregatorFrequencyTest() {
        super(new Java7Aggregator());
    }

}
