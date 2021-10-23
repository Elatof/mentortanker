package com.korbiak.mentorship.io.task2;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class DiskAnalyzer {
    //for s count for case 1
    private int sCount = 0;
    private File sFile;

    //for 5-top file by size for case 2
    private final List<File> files = new LinkedList<>();

    //for average file size for case 3
    private long countFile = 0;
    private long sumFiles = 0;

    //for sum of folders and files for case 4
    private char targetSymbol;
    private int sumFilesFolders = 0;

    public String getFileWithMaximumNumberOfSLetter(String inputDirectory) {
        File dir = new File(inputDirectory);
        iterateByFile(dir, 1);

        if (sFile == null) {
            return "Not found :((";
        }
        return sFile.getAbsolutePath() + " with count s:" + sCount;
    }

    public Map<String, Long> getFilesWithMaximumSize(String inputDirectory) {
        File dir = new File(inputDirectory);
        iterateByFile(dir, 2);

        return files.stream().collect(Collectors.toMap(File::getAbsolutePath, File::getUsableSpace));
    }

    public long getAverageSizeOfDirectory(String inputDirectory) {
        File dir = new File(inputDirectory);
        iterateByFile(dir, 3);

        return sumFiles / countFile;
    }

    public int getNumberOfFilesAndFolders(String inputDirectory, char symbol) {
        File dir = new File(inputDirectory);
        this.targetSymbol = symbol;
        iterateByFile(dir, 4);

        return sumFilesFolders;
    }

    private void iterateByFile(File file, int numberCase) {
        if (file.isFile()) {
            executeCounting(file, numberCase);
            System.out.println(file.getAbsolutePath());
        }
        if (file.isDirectory() && file.listFiles() != null) {
            countSumOfFilesAndFolder(file);
            Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .forEach(file1 -> iterateByFile(file1, numberCase));
        }
    }

    private void countSSymbol(File file) {
        int currentSCount = Utils.countSymbolInString(file.getName(), 's');
        if (currentSCount > this.sCount) {
            this.sCount = currentSCount;
            this.sFile = file;
        }
    }

    private void save5topFiles(File file) {
        files.add(file);
        if (files.size() > 5) {
            files.sort(Comparator.comparingLong(File::getUsableSpace));
            files.remove(5);
        }
    }

    private void countAverage(File file) {
        countFile++;
        sumFiles += file.getUsableSpace();
    }

    private void countSumOfFilesAndFolder(File file) {
        char[] toCharArray = file.getName().toCharArray();
        if (toCharArray[0] == targetSymbol) {
            sumFilesFolders++;
        }
    }

    private void executeCounting(File file, int numberCase) {
        switch (numberCase) {
            case 1:
                countSSymbol(file);
                break;
            case 2:
                save5topFiles(file);
                break;
            case 3:
                countAverage(file);
                break;
            case 4:
                countSumOfFilesAndFolder(file);
                break;
            default:
        }
    }
}
