package com.korbiak.mentorship.java8;

import com.korbiak.mentorship.java8.recursivetasks.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Java7ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumIntegerRecursiveTask sumRecursiveTask = new SumIntegerRecursiveTask(numbers);

        return forkJoinPool.invoke(sumRecursiveTask);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FrequentWordsPairRecursiveTask frequentWordsRecursiveTask = new FrequentWordsPairRecursiveTask(
                new ArrayList<>(new HashSet<>(words)), words);
        List<Pair<String, Long>> pairs = forkJoinPool.invoke(frequentWordsRecursiveTask);

        SortListRecursiveAction<Pair<String, Long>> sortRecursiveAction =
                new SortListRecursiveAction<>(pairs,
                        new Comparator<Pair<String, Long>>() {
                            @Override
                            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                                int comparison = Long.compare(o2.getValue(), o1.getValue());
                                return comparison == 0 ? o1.getKey().compareTo(o2.getKey()) : comparison;
                            }
                        });
        forkJoinPool.invoke(sortRecursiveAction);

        return pairs.size() < limit ? pairs : pairs.subList(0, (int) limit);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        UpperCaseRecursiveTask upperCaseRecursiveTask = new UpperCaseRecursiveTask((words));
        List<String> wordsInUpperCase = forkJoinPool.invoke(upperCaseRecursiveTask);

        List<String> uniqueWords = new ArrayList<>(new HashSet<>(wordsInUpperCase));
        DuplicatesRecursiveTask duplicatesRecursiveTask = new DuplicatesRecursiveTask(uniqueWords, wordsInUpperCase);
        List<String> result = forkJoinPool.invoke(duplicatesRecursiveTask);

        SortListRecursiveAction<String> sortRecursiveAction = new SortListRecursiveAction<>(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int comparison = Integer.compare(o1.length(), o2.length());
                return comparison == 0 ? o1.compareTo(o2) : comparison;
            }
        });
        forkJoinPool.invoke(sortRecursiveAction);

        return result.size() < limit ? result : result.subList(0, (int) limit);
    }
}
