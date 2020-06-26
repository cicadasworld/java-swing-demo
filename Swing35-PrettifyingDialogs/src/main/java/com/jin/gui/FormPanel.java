package com.jin.gui;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class FormPanel extends JPanel {

    private final JLabel nameLbl;
    private final JLabel occupationLbl;
    private final JTextField nameField;
    private final JTextField occupationField;
    private final JButton okBtn;
    private final JList<AgeCategory> ageList;
    private final JComboBox<String> empCombo;
    private final JCheckBox citizenCheck;
    private final JLabel taxLbl;
    private final JTextField taxField;
    private final JRadioButton maleRadio;
    private final JRadioButton femaleRadio;
    private final ButtonGroup genderGroup;
    private FormListener formListener;

    public FormPanel() {
        Dimension dim = this.getPreferredSize();
        dim.width = 250;
        this.setPreferredSize(dim);

        nameLbl = new JLabel("Name: ");
        occupationLbl = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList<>();
        empCombo = new JComboBox<>();
        citizenCheck = new JCheckBox();
        taxLbl = new JLabel("Tax ID: ");
        taxField = new JTextField(10);
        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        okBtn = new JButton("OK");

        // set up mnemonic
        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLbl.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLbl.setLabelFor(nameField);

        // set up list box
        DefaultListModel<AgeCategory> ageModel = new DefaultListModel<>();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 to over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110, 68));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // set up combo box
        DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<>();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);

        // set up check box for tax ID
        taxLbl.setEnabled(false);
        taxField.setEnabled(false);
        citizenCheck.addActionListener(e -> {
            boolean isTicked = citizenCheck.isSelected();
            taxLbl.setEnabled(isTicked);
            taxField.setEnabled(isTicked);
        });

        // set up gender group
        maleRadio.setSelected(true);
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        // set up ok event
        okBtn.addActionListener(e -> {
            String name = nameField.getText();
            String occupation = occupationField.getText();
            AgeCategory ageCat = ageList.getSelectedValue();
            String empCat = (String) empCombo.getSelectedItem();
            boolean usCitizen = citizenCheck.isSelected();
            String taxId = taxField.getText();
            String gender = genderGroup.getSelection().getActionCommand();
            FormEvent ev = new FormEvent(this, name, occupation, ageCat.id, empCat, usCitizen, taxId, gender);
            if (formListener != null) {
                formListener.formEventOccurred(ev);
            }
        });

        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        TitledBorder innerBorder = BorderFactory.createTitledBorder("Add Person");
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    private void layoutComponents() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ////// First row //////
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(nameLbl, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(nameField, gc);

        ////// Second row //////
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(occupationLbl, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(occupationField, gc);

        ////// Next row //////
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(ageList, gc);

        ////// Next row //////
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(new JLabel("US citizen: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(citizenCheck, gc);

        ////// Next row //////
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(taxLbl, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(taxField, gc);

        ////// Next row //////
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(empCombo, gc);

        ////// Next row //////
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        this.add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(maleRadio, gc);

        ////// Next row //////
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(femaleRadio, gc);

        ////// Next row //////
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy += 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(okBtn, gc);
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
}

class AgeCategory {

    int id;
    String text;

    AgeCategory(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
