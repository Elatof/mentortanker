package com.korbiak.mentorship.io.task3;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FastFileMoverWithBuffer implements FastFileMover {
    @Override
    public void moveFile(String fromPath, String toPath) {
        log.info("Start moving file from {} to {}", fromPath, toPath);
        File originalFile = new File(fromPath);
        File fileToMove = new File(toPath);

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(originalFile), 1025);
             BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(fileToMove), 1025)) {
            log.info("Copying data of target file...");
            int length;
            while ((length = inputStream.read()) != -1) {
                fileOutputStream.write(length);
            }
        } catch (IOException e) {
            log.error("Error while moving target file {} : {}", fromPath, e.getMessage());
        }
        if (originalFile.delete()) {
            log.info("Successfully moved file to new path: {}", toPath);
        }
    }
}
