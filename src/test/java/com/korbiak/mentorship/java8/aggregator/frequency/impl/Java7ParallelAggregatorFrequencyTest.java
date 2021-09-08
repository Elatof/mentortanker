package com.korbiak.mentorship.java8.aggregator.frequency.impl;

import com.korbiak.mentorship.java8.Java7ParallelAggregator;
import com.korbiak.mentorship.java8.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7ParallelAggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java7ParallelAggregatorFrequencyTest() {
        super(new Java7ParallelAggregator());
    }
}
