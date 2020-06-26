package com.jin.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

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

        layoutControls();

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

        setSize(300, 230);
        setLocationRelativeTo(parent);
    }

    private void layoutControls() {
        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        controlsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        Insets rightPadding = new Insets(0, 0, 0, 15);
        Insets noPadding = new Insets(0, 0, 0, 0);

        ////// First row //////
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0; // first column
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("User: "), gc);

        gc.gridx += 1; // second column
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(userField, gc);

        ////// Next row //////
        gc.gridy += 1;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0; // first column
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Password: "), gc);

        gc.gridx += 1; // second column
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(passField, gc);

        ////// Next row //////
        gc.gridy += 1;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0; // first column
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Port: "), gc);

        gc.gridx += 1; // second column
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(portSpinner, gc);

        ////// Buttons Panel //////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        // Add sub panels (controls panel and buttons panel) to dialog
        this.setLayout(new BorderLayout());
        this.add(controlsPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
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
