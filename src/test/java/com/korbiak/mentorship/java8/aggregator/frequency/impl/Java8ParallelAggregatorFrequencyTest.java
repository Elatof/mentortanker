package com.korbiak.mentorship.java8.aggregator.frequency.impl;

import com.korbiak.mentorship.java8.Java8ParallelAggregator;
import com.korbiak.mentorship.java8.aggregator.frequency.JavaAggregatorFrequencyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorFrequencyTest extends JavaAggregatorFrequencyTest {

    public Java8ParallelAggregatorFrequencyTest() {
        super(new Java8ParallelAggregator());
    }
}
