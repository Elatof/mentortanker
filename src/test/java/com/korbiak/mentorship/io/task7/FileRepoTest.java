package com.korbiak.mentorship.io.task7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileRepoTest {
    private static FileRepo fileRepo;

    private static final String TEST_FILE_PATH = "src/test/resources/io/task7/testFile.jfif";
    private static final String RESULT_FILE_PATH = "src/test/resources/io/task7/testFileResult.jfif";


    @BeforeAll
    public static void setUpRepo() {
        fileRepo = new FileRepo(System.getenv("user"),
                System.getenv("password"));
    }

    @Test
    void saveFileTest() {
        File testFile = new File(TEST_FILE_PATH);
        boolean result = fileRepo.saveFile(new FileModel(testFile));
        Assertions.assertTrue(result);
    }

    @Test
    void getFileTest() {
        File fileById = fileRepo.getFileById(6);
        Assertions.assertNull(fileById);
    }

    @Test
    void getFileV2Test() {
        FileModel fileById = fileRepo.getFileByIdV2(6, RESULT_FILE_PATH);
        Assertions.assertNotNull(fileById);
    }
}
