package com.korbiak.mentorship.io.task2;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static DiskAnalyzer diskAnalyzer;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String RESULT_FILE_PATH = "src/main/resources/io/diskAnalyzerResult.txt";

    public static void main(String[] args) {
        System.out.println("Enter path to input:");
        String inputDirectory = scanner.nextLine();
        System.out.println(
            "1 -Search for the file name with the maximum number of letters 's' in the name\n" +
            "2 -Print largest files (by size in bytes).\n" +
            "3 -The average file size in the specified directory\n" +
            "4 -The number of files and folders,begin with input letter.\n");
        System.out.println("Enter number of function:");
        diskAnalyzer = new DiskAnalyzer();

        switch (scanner.nextInt()) {
            case 1:
                callFirstCase(inputDirectory);
                break;
            case 2:
                callSecondCase(inputDirectory);
                break;
            case 3:
                callThirdCase(inputDirectory);
                break;
            case 4:
                callFourthCase(inputDirectory);
                break;
            default:
                System.out.println("Incorrect number!");
        }
    }

    private static void callFirstCase(String inputDirectory) {
        String result = diskAnalyzer.getFileWithMaximumNumberOfSLetter(inputDirectory);
        System.out.println(result);
        Utils.writeToFile(RESULT_FILE_PATH, String.format("File with maximum number of letter: 's' - %s", result));
    }

    private static void callSecondCase(String inputDirectory) {
        Map<String, Long> result = diskAnalyzer.getFilesWithMaximumSize(inputDirectory);
        System.out.println(result);
        Utils.writeToFile(RESULT_FILE_PATH, String.format("Files with maximum size in bytes: %s", result));
    }

    private static void callThirdCase(String inputDirectory) {
        long result = diskAnalyzer.getAverageSizeOfDirectory(inputDirectory);
        System.out.println(result);
        Utils.writeToFile(RESULT_FILE_PATH, String.format("Average size in directory in bytes: %s - %s", inputDirectory, result));
    }

    private static void callFourthCase(String inputDirectory) {
        System.out.println("Enter letter:");
        char letter = scanner.next().charAt(0);
        int sum = diskAnalyzer.getNumberOfFilesAndFolders(inputDirectory, letter);
        System.out.println(sum);
        Utils.writeToFile(RESULT_FILE_PATH, String.format("Number of files and folders with: %s", sum));
    }
}
