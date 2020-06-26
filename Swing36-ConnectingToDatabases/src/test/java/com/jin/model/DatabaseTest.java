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
}
