package com.jin;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class FormPanel extends JPanel {

    public FormPanel() {
        Dimension dim = this.getPreferredSize();
        dim.width = 250;
        this.setPreferredSize(dim);

        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        TitledBorder innerBorder = BorderFactory.createTitledBorder("Add Person");
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
