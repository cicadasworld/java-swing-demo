package com.jin.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DatabaseTest {

    private Database db;

    @BeforeEach
    void start() {
        try {
            db = new Database();
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void end() {
        try {
            db = new Database();
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void save() {
        try {
            db.addPerson(new Person("Sue", "artist", AgeCategory.adult, EmploymentCategory.selfEmployed, false, null, Gender.female));
            db.addPerson(new Person("Joe", "lion tamer", AgeCategory.adult, EmploymentCategory.employed, true, "777", Gender.male));
            db.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void load() {
        try {
            db.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
