package com.jin.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

    private List<Person> people;
    private Connection conn;

    public Database() {
        this.people = new LinkedList<>();
    }

    public void connect() throws Exception {
        if (conn != null) {
            return;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/swingtest?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Connected: " + conn);
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }
    }

    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Can't close connection");
            }
        }
    }

    public void save() throws SQLException {
        String checkSql = "select count(*) as count from people where id=?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        for (Person person : people) {
            int id = person.getId();
            checkStmt.setInt(1, id); // sql start with 1 not zero
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);
            System.out.println("Count for person with ID " + id + " is " + count);
        }
        checkStmt.close();
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public void saveToFile(File file) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        Person[] persons = people.toArray(new Person[people.size()]);
        oos.writeObject(persons);
    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Person[] persons = (Person[]) ois.readObject();
        people.clear();
        people.addAll(Arrays.asList(persons));
    }

    public void removePerson(int index) {
        this.people.remove(index);
    }
}
