package com.korbiak.mentorship.io.task6;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ProcedureCall {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mentorship_db";
    private final Properties properties;

    public ProcedureCall(String user, String password) {
        Properties inputProperties = new Properties();
        inputProperties.put("user", user);
        inputProperties.put("password", password);
        this.properties = inputProperties;
    }

    public Map<String, String> callCountSizeOfDb() {
        Map<String, String> values = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             CallableStatement statement = connection.prepareCall("call count_size_of_db(?, ?, ?)")) {
            statement.setInt(1, 0);
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setInt(2, 0);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.setString(3, "");
            statement.registerOutParameter(3, Types.VARCHAR);
            statement.execute();

            values.put("totalSize", String.valueOf(statement.getInt(1)));
            values.put("numberOfTable", String.valueOf(statement.getInt(2)));
            values.put("allTables", statement.getString(3));
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
        }
        return values;
    }

    public Map<String, String> callGetTableInfo(String tableName) {
        Map<String, String> values = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             CallableStatement statement = connection.prepareCall("call tableInfo(?, ?, ?, ?)")) {
            statement.setString(1, tableName);
            statement.setInt(2, 0);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.setInt(3, 0);
            statement.registerOutParameter(3, Types.INTEGER);
            statement.setString(4, "");
            statement.registerOutParameter(4, Types.VARCHAR);
            statement.execute();

            values.put("numberOfColumns", String.valueOf(statement.getInt(2)));
            values.put("tableSize", String.valueOf(statement.getInt(3)));
            values.put("columnsName", statement.getString(4));
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
        }
        return values;
    }
}
