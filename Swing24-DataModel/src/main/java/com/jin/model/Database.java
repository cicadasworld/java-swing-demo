package com.jin.model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Person> people;

    public Database() {
        this.people = new ArrayList<>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }
}
