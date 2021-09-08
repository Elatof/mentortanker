package com.korbiak.mentorship.java8.aggregator.suits;

import com.korbiak.mentorship.java8.aggregator.duplicates.impl.Java7ParallelAggregatorDuplicatesTest;
import com.korbiak.mentorship.java8.aggregator.frequency.impl.Java7ParallelAggregatorFrequencyTest;
import com.korbiak.mentorship.java8.aggregator.sum.impl.Java7ParallelAggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7ParallelAggregatorFrequencyTest.class,
        Java7ParallelAggregatorSumTest.class,
        Java7ParallelAggregatorDuplicatesTest.class,
})
public class TestJava7ParallelAggregator {
}
