package com.korbiak.mentorship.java8.aggregator.sum.impl;

import com.korbiak.mentorship.java8.Java7Aggregator;
import com.korbiak.mentorship.java8.aggregator.sum.JavaAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7AggregatorSumTest extends JavaAggregatorSumTest {

    public Java7AggregatorSumTest() {
        super(new Java7Aggregator());
    }

}
