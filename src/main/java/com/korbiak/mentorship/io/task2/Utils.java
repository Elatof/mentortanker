package com.korbiak.mentorship.io.task2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static int countSymbolInString(String str, char symbol) {
        return (int) str.chars().filter(ch -> ch == symbol).count();
    }

    public static void writeToFile(String filePath, String result) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
