package com.korbiak.mentorship.java8.recursivetasks;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DuplicatesRecursiveTask extends RecursiveTask<List<String>> {

    private static final int THRESHOLD = 1;
    private final List<String> uniqueWords;
    private final List<String> upperCaseWords;

    public DuplicatesRecursiveTask(List<String> uniqueWords, List<String> upperCaseWords) {
        this.uniqueWords = uniqueWords;
        this.upperCaseWords = upperCaseWords;
    }

    @Override
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        if (uniqueWords.size() > THRESHOLD) {
            Collection<DuplicatesRecursiveTask> subtasks = ForkJoinTask.invokeAll(createSubTasks());
            for (DuplicatesRecursiveTask subtask : subtasks) {
                result.addAll(subtask.join());
            }
        } else {
            result.addAll(processing());
        }
        return result;
    }

    private List<DuplicatesRecursiveTask> createSubTasks() {
        List<DuplicatesRecursiveTask> subtasks = new ArrayList<>();
        subtasks.add(new DuplicatesRecursiveTask(uniqueWords.subList(0, uniqueWords.size() / 2), upperCaseWords));
        subtasks.add(new DuplicatesRecursiveTask(uniqueWords.subList(uniqueWords.size() / 2, uniqueWords.size()), upperCaseWords));

        return subtasks;
    }

    private List<String> processing() {
        List<String> result = new ArrayList<>();
        for (String word: uniqueWords){
            if(Collections.frequency(upperCaseWords, word) > 1){
                result.add(word);
            }
        }
        return result;
    }
}
