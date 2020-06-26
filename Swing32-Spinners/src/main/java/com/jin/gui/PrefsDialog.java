package com.jin.gui;

import java.awt.*;

import javax.swing.*;

public class PrefsDialog extends JDialog {

    private final JButton okButton;
    private final JButton cancelButton;
    private final JSpinner portSpinner;
    private final SpinnerNumberModel spinnerModel;

    public PrefsDialog(JFrame parent) {
        super(parent, "Preferences", false);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        ////// First row //////
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0; // first column
        this.add(new JLabel("Port: "), gc);

        gc.gridx += 1; // second column
        this.add(portSpinner, gc);

        ////// Next row //////
        gc.gridy += 1;

        gc.gridx = 0; // first column
        this.add(okButton, gc);

        gc.gridx += 1; // second column
        this.add(cancelButton, gc);

        okButton.addActionListener(e -> {
            Integer value = (Integer) portSpinner.getValue();
            System.out.println(value);
            setVisible(false);
        });

        cancelButton.addActionListener(e -> setVisible(false));

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}
