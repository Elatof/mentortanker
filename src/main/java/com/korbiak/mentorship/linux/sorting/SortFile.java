package com.korbiak.mentorship.linux.sorting;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class SortFile {

    private final String path;
    private final File file;

    public SortFile(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public void sortFile(String sortedFilePath) {
        List<String> allLines = readAllLinesFromFile();
        List<String> sortedList = allLines.stream()
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.toList());
        writeAllLinesToFile(sortedList, sortedFilePath);
    }

    private void writeAllLinesToFile(List<String> sortedList, String sortedFilePath) {
        Path targetPath = new File(sortedFilePath).toPath();
        try {
            Files.write(targetPath, sortedList, Charset.defaultCharset());
        } catch (IOException e) {
            log.error("Error with writing file by path: {}", targetPath);
            e.printStackTrace();
        }
    }

    private List<String> readAllLinesFromFile() {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            log.error("Error with reading file by path: {}", file.getPath());
            e.printStackTrace();
        }
        return strings;
    }
}
