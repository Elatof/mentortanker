package com.korbiak.mentorship.io.task7;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BlobUtils {

    public static File buildFile(String path, Blob blob) {
        File outputFile = new File(path);
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            IOUtils.copy(blob.getBinaryStream(), outputStream);
        } catch (IOException | SQLException e) {
            log.error("IOException:SQLException {}", e.getMessage());
        }
        return outputFile;
    }
}
