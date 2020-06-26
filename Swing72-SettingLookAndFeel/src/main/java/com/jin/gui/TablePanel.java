package com.jin.gui;

import com.jin.model.EmploymentCategory;
import com.jin.model.Person;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

public class TablePanel extends JPanel {

    private final JTable table;
    private final PersonTableModel tableModel;
    private final JPopupMenu popup;
    private PersonTableListener personTableListener;

    public TablePanel() {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Delete row");
        popup.add(removeItem);

        table.setDefaultRenderer(EmploymentCategory.class, new EmploymentCategoryRenderer());
        table.setDefaultEditor(EmploymentCategory.class, new EmploymentCategoryEditor());
        table.setRowHeight(25);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int rowIndex = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(rowIndex, rowIndex); //selection from start rowIndex to end rowIndex
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(table, e.getX(), e.getY());
                }
            }
        });
        removeItem.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (personTableListener != null) {
                personTableListener.rowDeleted(rowIndex);
                tableModel.fireTableRowsDeleted(rowIndex, rowIndex);
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> people) {
        tableModel.setData(people);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }

    public void setPersonTableListener(PersonTableListener listener) {
        this.personTableListener = listener;
    }
}
