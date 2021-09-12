package com.korbiak.mentorship.java8;

import javafx.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream()
                .parallel()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.stream()
                .parallel()
                .distinct()
                .map(word -> new Pair<>(word, (long) Collections.frequency(words, word)))
                .sorted((o1, o2) -> {
                    int comparison = Long.compare(o2.getValue(), o1.getValue());
                    return comparison == 0 ? o1.getKey().compareTo(o2.getKey()) : comparison;
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> upperCaseWords = words.stream()
                .parallel()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        return words.stream()
                .parallel()
                .map(String::toUpperCase)
                .distinct()
                .filter(word -> Collections.frequency(upperCaseWords, word) > 1)
                .sorted(Comparator.comparingInt(String::length).thenComparing(String::compareTo))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
