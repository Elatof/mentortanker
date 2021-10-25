package com.korbiak.mentorship.io.task4.model;

import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class TutorMapper {

    public static Tutor mapTutor(ResultSet set) {
        try {
            return new Tutor(set.getInt("id"),
                    set.getString("name"),
                    set.getString("surname"));
        } catch (SQLException e) {
            log.error("Error mapping tutor object: {}", e.getMessage());
        }
        return new Tutor();
    }
}
