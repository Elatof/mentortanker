package com.korbiak.mentorship.java8.aggregator.duplicates.impl;

import com.korbiak.mentorship.java8.Java8Aggregator;
import com.korbiak.mentorship.java8.aggregator.duplicates.JavaAggregatorDuplicatesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8AggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java8AggregatorDuplicatesTest() {
        super(new Java8Aggregator());
    }
}

