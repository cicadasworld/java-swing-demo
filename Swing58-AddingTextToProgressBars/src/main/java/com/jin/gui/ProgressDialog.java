package com.jin.gui;

import java.awt.*;

import javax.swing.*;

public class ProgressDialog extends JDialog {

    private final JButton cancelButton;
    private final JProgressBar progressBar;

    public ProgressDialog(Window parent) {
        super(parent, "Messages Downloading...", ModalityType.APPLICATION_MODAL);

        cancelButton = new JButton("Cancel");
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMaximum(10);
        progressBar.setString("Retrieving messages...");

        //progressBar.setIndeterminate(true);

        setLayout(new FlowLayout());

        Dimension size = cancelButton.getPreferredSize();
        size.width = 400;
        progressBar.setPreferredSize(size);

        add(progressBar);
        add(cancelButton);
        pack();

        setLocationRelativeTo(parent);
    }

    public void setMaximum(int count) {
        progressBar.setMaximum(count);
    }

    public void setValue(int value) {
        int progress = 100 * value / progressBar.getMaximum();
        progressBar.setString(String.format("%d%%", progress));
        progressBar.setValue(value);
    }

    @Override
    public void setVisible(boolean visible) {
        SwingUtilities.invokeLater(()-> {
            if (!visible) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                progressBar.setValue(0);
            }
            super.setVisible(visible);
        });
    }
}
