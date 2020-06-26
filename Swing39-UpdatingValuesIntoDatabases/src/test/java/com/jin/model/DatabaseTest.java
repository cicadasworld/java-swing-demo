package com.jin.model;

import org.junit.jupiter.api.Test;

public class DatabaseTest {

    @Test
    void connectToDb() {
        System.out.println("Running database test");
        Database db = new Database();
        try {
            db.connect();
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.disconnect();
    }

    @Test
    void save() {
        Database db = new Database();
        try {
            db.connect();
            db.addPerson(new Person("Joe", "lion tamer", AgeCategory.adult, EmploymentCategory.employed, true, "777", Gender.male));
            db.addPerson(new Person("Sue", "artist", AgeCategory.adult, EmploymentCategory.selfEmployed, false, null, Gender.female));
            db.save();
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
