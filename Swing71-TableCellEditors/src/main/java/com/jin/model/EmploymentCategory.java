package com.jin.model;

public enum EmploymentCategory {
    employed("employed"),
    selfEmployed("self employed"),
    unemployed("unemployed"),
    other("other");

    private String text;

    EmploymentCategory(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
