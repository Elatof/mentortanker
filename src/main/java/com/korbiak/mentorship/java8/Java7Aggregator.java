package com.korbiak.mentorship.java8;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> pairList = new ArrayList<>();
        List<String> uniqueWords = new ArrayList<>(new HashSet<>(words));

        for (String word : uniqueWords) {
            pairList.add(new Pair<>(word, (long) Collections.frequency(words, word)));
        }

        // only an anonymous class in java 7
        // pairList.sort in java 8
        Collections.sort(pairList, new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                int comparison = Long.compare(o2.getValue(), o1.getValue());
                if (comparison == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return comparison;
            }
        });

        return pairList.size() < limit ? pairList : pairList.subList(0, (int) limit);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> duplicateList = new ArrayList<>();
        List<String> upperCaseWords = new ArrayList<>();
        for (String word : words) {
            upperCaseWords.add(word.toUpperCase(Locale.ROOT));
        }
        List<String> uniqueWords = new ArrayList<>(new HashSet<>(upperCaseWords));

        for (String word : uniqueWords) {
            if (Collections.frequency(upperCaseWords, word) > 1) {
                duplicateList.add(word);
            }
        }

        // only an anonymous class in java 7
        // duplicateList.sort in java 8
        Collections.sort(duplicateList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int comparison = Integer.compare(o1.length(), o2.length());
                if (comparison == 0) {
                    return o1.compareTo(o2);
                }
                return comparison;
            }
        });

        return duplicateList.size() < limit ? duplicateList : duplicateList.subList(0, (int) limit);
    }
}
