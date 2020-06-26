package com.jin.gui;

import com.jin.model.EmploymentCategory;
import com.jin.model.Person;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel {

    private List<Person> people;

    private final String[] colNames = new String[] {"ID", "Name", "Occupation", "Age Category",
            "EmploymentCategory", "US Citizen", "Tax ID"};

    public void setData(List<Person> people) {
        this.people = people;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 1:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        if (people == null) {
            return;
        }
        Person person = people.get(row);
        switch (col) {
            case 1:
                person.setName((String) aValue);
                break;
            case 4:
                person.setEmpCategory((EmploymentCategory) aValue);
                break;
            case 5:
                person.setUsCitizen((Boolean) aValue);
                break;
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
            case 2:
            case 3:
            case 6:
                return String.class;
            case 4:
                return EmploymentCategory.class;
            case 5:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Person person = people.get(row);
        switch (col) {
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCategory();
            case 4:
                return person.getEmpCategory();
            case 5:
                return person.isUsCitizen();
            case 6:
                return person.getTaxId();
        }
        return null;
    }
}
