package com.korbiak.mentorship.java8.aggregator.suits;

import com.korbiak.mentorship.java8.aggregator.duplicates.impl.Java7AggregatorDuplicatesTest;
import com.korbiak.mentorship.java8.aggregator.frequency.impl.Java7AggregatorFrequencyTest;
import com.korbiak.mentorship.java8.aggregator.sum.impl.Java7AggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7AggregatorFrequencyTest.class,
        Java7AggregatorSumTest.class,
        Java7AggregatorDuplicatesTest.class,
})
public class TestJava7Aggregator {
}
