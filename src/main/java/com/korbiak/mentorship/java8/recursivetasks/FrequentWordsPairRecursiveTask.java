package com.korbiak.mentorship.java8.recursivetasks;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FrequentWordsPairRecursiveTask extends RecursiveTask<List<Pair<String, Long>>> {

    private static final int THRESHOLD = 1;
    private final List<String> uniqueWords;
    private final List<String> words;

    public FrequentWordsPairRecursiveTask(List<String> uniqueWords, List<String> words) {
        this.uniqueWords = uniqueWords;
        this.words = words;
    }

    @Override
    protected List<Pair<String, Long>> compute() {
        List<Pair<String, Long>> pairs = new ArrayList<>();
        if (uniqueWords.size() > THRESHOLD && words.size() > THRESHOLD) {
            Collection<FrequentWordsPairRecursiveTask> subtasks = ForkJoinTask.invokeAll(createSubTasks());

            for (FrequentWordsPairRecursiveTask subtask : subtasks) {
                pairs.addAll(subtask.join());
            }
        } else {
            pairs.addAll(processing());
        }
        return pairs;
    }

    private List<FrequentWordsPairRecursiveTask> createSubTasks() {
        List<FrequentWordsPairRecursiveTask> subtasks = new ArrayList<>();
        subtasks.add(new FrequentWordsPairRecursiveTask(uniqueWords.subList(0, uniqueWords.size() / 2), words));
        subtasks.add(new FrequentWordsPairRecursiveTask(uniqueWords.subList(uniqueWords.size() / 2, uniqueWords.size()), words));

        return subtasks;
    }

    private List<Pair<String, Long>> processing() {
        List<Pair<String, Long>> pairs = new ArrayList<>();

        for (String word : uniqueWords) {
            pairs.add(new Pair<>(word, (long) Collections.frequency(words, word)));
        }
        return pairs;
    }
}
