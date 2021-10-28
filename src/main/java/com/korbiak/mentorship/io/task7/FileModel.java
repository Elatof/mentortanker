package com.korbiak.mentorship.io.task7;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.sql.Date;

@Data
@AllArgsConstructor
public class FileModel {

    private int id;
    private String name;
    private File content;
    private Date expireTime;

    public FileModel(File content) {
        this.name = content.getName();
        this.content = content;
    }
}
