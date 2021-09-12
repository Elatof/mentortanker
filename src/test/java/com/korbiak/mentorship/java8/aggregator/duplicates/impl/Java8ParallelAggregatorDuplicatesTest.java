package com.korbiak.mentorship.java8.aggregator.duplicates.impl;

import com.korbiak.mentorship.java8.Java8ParallelAggregator;
import com.korbiak.mentorship.java8.aggregator.duplicates.JavaAggregatorDuplicatesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Java8ParallelAggregatorDuplicatesTest extends JavaAggregatorDuplicatesTest {

    public Java8ParallelAggregatorDuplicatesTest() {
        super(new Java8ParallelAggregator());
    }

}
