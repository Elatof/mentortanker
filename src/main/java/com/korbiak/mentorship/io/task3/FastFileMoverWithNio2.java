package com.korbiak.mentorship.io.task3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class FastFileMoverWithNio2 implements FastFileMover {
  @Override
  public void moveFile(String fromPath, String toPath) {
    log.info("Start moving file from {} to {}", fromPath, toPath);
    Path originalFile = Paths.get(fromPath);
    Path fileToMove = Paths.get(toPath);
    try {
      Files.move(originalFile, fileToMove, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      log.error("Error while moving target file {} : {}", fromPath, e.getMessage());
    }
    log.info("Successfully moved file to new path: {}", toPath);
  }
}
