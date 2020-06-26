package com.jin;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class FormPanel extends JPanel {

    private final JLabel nameLbl;
    private final JLabel occupationLbl;
    private final JTextField nameField;
    private final JTextField occupationField;
    private final JButton okBtn;

    public FormPanel() {
        Dimension dim = this.getPreferredSize();
        dim.width = 250;
        this.setPreferredSize(dim);

        nameLbl = new JLabel("Name: ");
        occupationLbl = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);

        okBtn = new JButton("OK");

        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        TitledBorder innerBorder = BorderFactory.createTitledBorder("Add Person");
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
