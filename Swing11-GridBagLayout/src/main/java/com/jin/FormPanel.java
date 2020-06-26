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

        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////// First row //////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(nameLbl, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(nameField, gc);

        ////// Second row //////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(occupationLbl, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(occupationField, gc);

        ////// Third row //////
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(okBtn, gc);

    }
}
