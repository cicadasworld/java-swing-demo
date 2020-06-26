package com.jin.gui;

import com.jin.model.EmploymentCategory;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class EmploymentCategoryRenderer implements TableCellRenderer {

    private final JComboBox<EmploymentCategory> combo;

    public EmploymentCategoryRenderer() {
        combo = new JComboBox<>(EmploymentCategory.values());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        combo.setSelectedItem(value);
        return combo;
    }
}
