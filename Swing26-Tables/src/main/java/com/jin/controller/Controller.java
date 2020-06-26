package com.jin.controller;

import com.jin.gui.FormEvent;
import com.jin.model.AgeCategory;
import com.jin.model.Database;
import com.jin.model.EmploymentCategory;
import com.jin.model.Gender;
import com.jin.model.Person;

import java.util.List;

public class Controller {

    private final Database db;

    public Controller() {
        this.db = new Database();
    }

    public List<Person> getPeople() {
        return db.getPeople();
    }

    public void addPerson(FormEvent ev) {
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCatId = ev.getAgeCategory();
        String empCat = ev.getEmpCategory();
        boolean usCitizen = ev.isUsCitizen();
        String taxId = ev.getTaxId();
        String gender = ev.getGender();

        AgeCategory ageCategory = null;
        switch (ageCatId) {
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;
        }

        EmploymentCategory empCategory;
        switch (empCat) {
            case "employed":
                empCategory = EmploymentCategory.employed;
                break;
            case "self-employed":
                empCategory = EmploymentCategory.selfEmployed;
                break;
            case "unemployed":
                empCategory = EmploymentCategory.unemployed;
                break;
            default:
                empCategory = EmploymentCategory.other;
                System.err.println("Unknown employment category: " + empCat);
                break;
        }

        Gender genderCat;
        if (gender.equals("male")) {
            genderCat = Gender.male;
        } else {
            genderCat = Gender.female;
        }

        Person person = new Person(name, occupation, ageCategory, empCategory, usCitizen, taxId, genderCat);
        db.addPerson(person);
    }
}
