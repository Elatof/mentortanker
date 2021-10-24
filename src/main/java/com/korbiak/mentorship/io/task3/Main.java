package com.korbiak.mentorship.io.task3;

public class Main {
    public static void main(String[] args) {
        FastFileMover fileMover;
        int fileMoverType = Integer.parseInt(args[0]);
        if (fileMoverType == 1) {
            fileMover = new FastFileMoverStream();
        } else if (fileMoverType == 2) {
            fileMover = new FastFileMoverWithBuffer();
        } else if (fileMoverType == 3) {
            fileMover = new FastFileMoverWithChannel();
        } else if (fileMoverType == 4) {
            fileMover = new FastFileMoverWithNio2();
        } else {
            throw new IllegalArgumentException("Not found file mover fo type: " + fileMoverType);
        }

        if (!args[1].isEmpty() && !args[2].isEmpty()) {
            fileMover.moveFile(args[1], args[2]);
        } else {
            throw new IllegalArgumentException("Path to file can not be empty");
        }
    }
}
