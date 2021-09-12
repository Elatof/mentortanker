package com.korbiak.mentorship.java8.recursivetasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class SumIntegerRecursiveTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private final List<Integer> numbers;

    public SumIntegerRecursiveTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (numbers.size() > THRESHOLD) {
            Collection<SumIntegerRecursiveTask> subtasks = ForkJoinTask.invokeAll(createSubTasks());
            for (SumIntegerRecursiveTask task : subtasks) {
                Integer resultOfTask = task.join();
                sum += resultOfTask;
            }
        } else {
            sum = processing();
        }
        return sum;
    }

    private List<SumIntegerRecursiveTask> createSubTasks() {
        List<SumIntegerRecursiveTask> subtasks = new ArrayList<>();
        subtasks.add(new SumIntegerRecursiveTask(numbers.subList(0, numbers.size() / 2)));
        subtasks.add(new SumIntegerRecursiveTask(numbers.subList(numbers.size() / 2, numbers.size())));

        return subtasks;
    }

    private Integer processing() {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }
}
