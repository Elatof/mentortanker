package com.korbiak.mentorship.java8.aggregator.sum.impl;

import com.korbiak.mentorship.java8.Java8Aggregator;
import com.korbiak.mentorship.java8.aggregator.sum.JavaAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8AggregatorSumTest extends JavaAggregatorSumTest {

    public Java8AggregatorSumTest() {
        super(new Java8Aggregator());
    }
}

