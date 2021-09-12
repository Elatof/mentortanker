package com.korbiak.mentorship.java8.recursivetasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class UpperCaseRecursiveTask extends RecursiveTask<List<String>> {

    private static final int THRESHOLD = 1;
    private final List<String> words;

    public UpperCaseRecursiveTask(List<String> words) {
        this.words = words;
    }

    @Override
    protected List<String> compute() {
        List<String> wordsInUpperCase = new ArrayList<>();
        if (words.size() > THRESHOLD) {
            Collection<UpperCaseRecursiveTask> subtasks = ForkJoinTask.invokeAll(createSubTasks());

            for (UpperCaseRecursiveTask subtask : subtasks) {
                wordsInUpperCase.addAll(subtask.join());
            }
        } else {
            wordsInUpperCase.addAll(processing());
        }
        return wordsInUpperCase;
    }

    private List<UpperCaseRecursiveTask> createSubTasks() {
        List<UpperCaseRecursiveTask> subtasks = new ArrayList<>();
        subtasks.add(new UpperCaseRecursiveTask(words.subList(0, words.size() / 2)));
        subtasks.add(new UpperCaseRecursiveTask(words.subList(words.size() / 2, words.size())));

        return subtasks;
    }

    private List<String> processing() {
        List<String> wordsInUpperCase = new ArrayList<>();

        for (String word : words) {
            wordsInUpperCase.add(word.toUpperCase(Locale.ROOT));
        }
        return wordsInUpperCase;
    }
}
