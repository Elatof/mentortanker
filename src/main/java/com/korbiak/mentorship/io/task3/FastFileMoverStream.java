package com.korbiak.mentorship.io.task3;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class FastFileMoverStream implements FastFileMover {

  @Override
  public void moveFile(String fromPath, String toPath) {
    log.info("Start moving file from {} to {}", fromPath, toPath);
    File originalFile = new File(fromPath);
    File fileToMove = new File(toPath);

    try (FileInputStream inputStream = new FileInputStream(originalFile);
         FileOutputStream fileOutputStream = new FileOutputStream(fileToMove)) {
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
