package com.jin;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private final String name;
    private final String occupation;
    private final int ageCategory;
    private final String empCategory;
    private final boolean usCitizen;
    private final String taxId;
    private final String gender;

    public FormEvent(Object source,
                     String name,
                     String occupation,
                     int ageCategory,
                     String empCategory,
                     boolean usCitizen,
                     String taxId,
                     String gender) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.empCategory = empCategory;
        this.usCitizen = usCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public String getEmpCategory() {
        return empCategory;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getGender() {
        return gender;
    }
}
