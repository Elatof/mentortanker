package com.korbiak.mentorship.io.task3;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FastFileMoverWithChannel implements FastFileMover {
    @Override
    public void moveFile(String fromPath, String toPath) {
        log.info("Start moving file from {} to {}", fromPath, toPath);
        File originalFile = new File(fromPath);
        File fileToMove = new File(toPath);
        try (FileChannel inputChannel = new FileInputStream(originalFile).getChannel();
             FileChannel outputChannel = new FileOutputStream(fileToMove).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(48);
            log.info("Copying data of target file...");
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    outputChannel.write(buffer);
                }
                buffer.clear();
            }
        } catch (IOException e) {
            log.error("Error while moving target file {} : {}", fromPath, e.getMessage());
        }
        originalFile.delete();
        log.info("Successfully moved file to new path: {}", toPath);
    }
}
