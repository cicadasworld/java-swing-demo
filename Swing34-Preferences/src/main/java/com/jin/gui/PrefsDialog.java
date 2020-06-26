package com.jin.gui;

import java.awt.*;

import javax.swing.*;

public class PrefsDialog extends JDialog {

    private final JButton okButton;
    private final JButton cancelButton;
    private final JSpinner portSpinner;
    private final SpinnerNumberModel spinnerModel;
    private final JTextField userField;
    private final JPasswordField passField;
    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent) {
        super(parent, "Preferences", false);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        userField = new JTextField(10);
        passField = new JPasswordField(10);
        passField.setEchoChar('*');

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        ////// First row //////
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0; // first column
        this.add(new JLabel("User: "), gc);

        gc.gridx += 1; // second column
        this.add(userField, gc);

        ////// Next row //////
        gc.gridy += 1;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0; // first column
        this.add(new JLabel("Password: "), gc);

        gc.gridx += 1; // second column
        this.add(passField, gc);

        ////// Next row //////
        gc.gridy += 1;
        gc.weightx = 1;
        gc.weighty = 1;

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
            String user = userField.getText();
            char[] password = passField.getPassword();
            Integer port = (Integer) portSpinner.getValue();
            if (prefsListener != null) {
                prefsListener.preferencesSet(user, new String(password), port);
            }
            setVisible(false);
        });

        cancelButton.addActionListener(e -> setVisible(false));

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }

    public void setDefaults(String user, String password, int port) {
        userField.setText(user);
        passField.setText(password);
        portSpinner.setValue(port);
    }
}
