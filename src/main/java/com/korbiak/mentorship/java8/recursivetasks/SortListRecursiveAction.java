package com.korbiak.mentorship.java8.recursivetasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class SortListRecursiveAction<T> extends RecursiveAction {

    private final List<T> list;
    private final Comparator<T> comparator;

    public SortListRecursiveAction(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.comparator = comparator;
    }

    @Override
    protected void compute() {
        int size = list.size();
        if (size < 2) {
            return;
        }
        List<T> left = new ArrayList<>(list.subList(0, size / 2));
        List<T> right = new ArrayList<>(list.subList(size / 2, size));
        invokeAll(new SortListRecursiveAction<>(left, comparator), new SortListRecursiveAction<>(right, comparator));

        merge(left, right);
    }

    public void merge(List<T> left, List<T> right) {
        int k = 0;
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) < 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }

}
