package com.korbiak.mentorship.io.task7;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

@Slf4j
public class FileRepo {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mentorship_db";
    private final Properties properties;

    private static final String SELECT_BLOB_FILE = "SELECT file_name, file_content, expire_time FROM public.files where id = ?";

    public FileRepo(String user, String password) {
        Properties inputProperties = new Properties();
        inputProperties.put("user", user);
        inputProperties.put("password", password);
        this.properties = inputProperties;
    }

    public boolean saveFile(FileModel file) {
        LocalDate futureDate = LocalDate.now().plusMonths(1);
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             CallableStatement statement = connection.prepareCall("call save_file(?, ?, ?)");
             FileInputStream fileInputStream = new FileInputStream(file.getContent())) {
            connection.setAutoCommit(false);
            statement.setString(1, file.getName());
            statement.setDate(2, Date.valueOf(futureDate));
            statement.setBlob(3, fileInputStream, file.getContent().length());
            statement.execute();
            connection.commit();
        } catch (SQLException | FileNotFoundException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
            return false;
        } catch (IOException e) {
            log.error("IOException : {}", e.getMessage());
            return false;
        }
        return true;
    }

    public File getFileById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             CallableStatement statement = connection.prepareCall("call get_file(?, ?, ?)");) {
            statement.setInt(1, id);
            statement.registerOutParameter(2, Types.DATE);
            statement.setDate(2, null);
            statement.registerOutParameter(3, Types.BIGINT);
            statement.setBlob(3, (Blob) null);
            statement.execute();

            Date date = statement.getDate(2);
            if (new Date(Calendar.getInstance().getTime().getTime()).after(date)) {
                log.error("This file is expired");
                return null;
            }
            Blob blob = statement.getBlob(3); // will be error notImplemented
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
            return null;
        }
        return null;
    }

    public FileModel getFileByIdV2(int id, String pathToFile) {
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             PreparedStatement statement = connection.prepareStatement(SELECT_BLOB_FILE)) {
            connection.setAutoCommit(false);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String name = resultSet.getString(1);
            Date date = resultSet.getDate(3);
            if (new Date(Calendar.getInstance().getTime().getTime()).after(date)) {
                log.error("This {} file is expired", name);
                return null;
            }
            Blob blob = resultSet.getBlob(2);
            connection.commit();
            File file = BlobUtils.buildFile(pathToFile, blob);
            return new FileModel(id, name, file, date);
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
            return null;
        }
    }
}
