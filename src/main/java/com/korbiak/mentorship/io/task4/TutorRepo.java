package com.korbiak.mentorship.io.task4;

import com.korbiak.mentorship.io.task4.model.Tutor;
import com.korbiak.mentorship.io.task4.model.TutorMapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.korbiak.mentorship.io.task4.TutorQuery.*;

@Slf4j
public class TutorRepo {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mentorship_db";
    private final Properties properties;

    public TutorRepo(String user, String password) {
        Properties inputProperties = new Properties();
        inputProperties.put("user", user);
        inputProperties.put("password", password);
        this.properties = inputProperties;
    }

    public List<Tutor> getAllTutors() {
        List<Tutor> tutorList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Tutor tutor = TutorMapper.mapTutor(resultSet);
                tutorList.add(tutor);
            }
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
        }
        return tutorList;
    }

    public Tutor getTutorById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return TutorMapper.mapTutor(resultSet);
            }
            return new Tutor();
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
        }
        return new Tutor();
    }

    public boolean insertTutor(Tutor tutor) {
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, tutor.getId());
            statement.setString(2, tutor.getName());
            statement.setString(3, tutor.getSurname());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
        }
        return false;
    }

    public int updateTutor(Tutor tutor) {
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, tutor.getName());
            statement.setString(2, tutor.getSurname());
            statement.setInt(3, tutor.getId());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
        }
        return 0;
    }

    public boolean deleteTutor(int tutorId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, properties);
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, tutorId);
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            log.error("SQLException : {}", throwables.getMessage());
        }
        return false;
    }
}
