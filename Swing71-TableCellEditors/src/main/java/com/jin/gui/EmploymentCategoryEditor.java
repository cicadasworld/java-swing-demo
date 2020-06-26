package com.jin.gui;

import com.jin.model.EmploymentCategory;

import java.awt.*;
import java.util.EventObject;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class EmploymentCategoryEditor extends AbstractCellEditor implements TableCellEditor {

    private final JComboBox<EmploymentCategory> combo;

    public EmploymentCategoryEditor() {
        combo = new JComboBox<>(EmploymentCategory.values());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        combo.setSelectedItem(value);
        combo.addActionListener(e -> fireEditingStopped());
        return combo;
    }

    @Override
    public Object getCellEditorValue() {
        return combo.getSelectedItem();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }
}
