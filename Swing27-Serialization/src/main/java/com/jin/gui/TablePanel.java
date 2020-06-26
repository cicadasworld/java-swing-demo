package com.jin.gui;

import com.jin.model.Person;

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class TablePanel extends JPanel {

    private final JTable table;
    private final PersonTableModel tableModel;

    public TablePanel() {
        tableModel = new PersonTableModel();
        this.table = new JTable(tableModel);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> people) {
        tableModel.setData(people);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
