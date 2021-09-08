package com.korbiak.mentorship.java8.aggregator.sum.impl;

import com.korbiak.mentorship.java8.Java8ParallelAggregator;
import com.korbiak.mentorship.java8.aggregator.sum.JavaAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorSumTest extends JavaAggregatorSumTest {

    public Java8ParallelAggregatorSumTest() {
        super(new Java8ParallelAggregator());
    }
}
