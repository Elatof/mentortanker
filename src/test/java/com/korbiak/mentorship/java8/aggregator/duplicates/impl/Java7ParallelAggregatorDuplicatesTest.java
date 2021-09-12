package com.korbiak.mentorship.java8.aggregator.duplicates.impl;

import com.korbiak.mentorship.java8.Java7ParallelAggregator;
import com.korbiak.mentorship.java8.aggregator.duplicates.JavaAggregatorDuplicatesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java7ParallelAggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java7ParallelAggregatorDuplicatesTest() {
        super(new Java7ParallelAggregator());
    }
}
