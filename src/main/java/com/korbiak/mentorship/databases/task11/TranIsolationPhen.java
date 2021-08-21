package com.korbiak.mentorship.databases.task11;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Properties;

@Slf4j
public class TranIsolationPhen {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mentorship_db";
    private final Properties properties;
    private static final int ISOLATION = Connection.TRANSACTION_READ_UNCOMMITTED;

    public TranIsolationPhen(String user, String password) {
        Properties inputProperties = new Properties();
        inputProperties.put("user", user);
        inputProperties.put("password", password);
        this.properties = inputProperties;
    }

    //Non-repeatable reads
    public void execute() {
        Thread thread1 = new Thread(this::executeRead);
        Thread thread2 = new Thread(this::executeUpdate);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void executeRead() {
        String query = "SELECT * FROM student WHERE student.id = 1";
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             Statement statement = connection.createStatement()) {
            connection.setTransactionIsolation(ISOLATION);
            connection.setAutoCommit(false);
            ResultSet resultSet1 = statement.executeQuery(query);
            printResultSet(resultSet1);
            try { Thread.sleep(1000); } catch (InterruptedException exception) { exception.printStackTrace(); }
            ResultSet resultSet2 = statement.executeQuery(query);
            printResultSet(resultSet2);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void executeUpdate() {
        String query = "UPDATE public.student SET name='newName999' WHERE id = 1";
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             Statement statement = connection.createStatement()) {
            connection.setTransactionIsolation(ISOLATION);
            try { Thread.sleep(500); } catch (InterruptedException exception) { exception.printStackTrace(); }
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printResultSet(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + metaData.getColumnName(i));
                }
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
