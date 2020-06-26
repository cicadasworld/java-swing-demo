package com.jin.gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class ProgressDialog extends JDialog {

    private final JButton cancelButton;
    private final JProgressBar progressBar;
    private ProgressDialogListener listener;

    public ProgressDialog(Window parent, String title) {
        super(parent, title, ModalityType.APPLICATION_MODAL);

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

        cancelButton.addActionListener(e -> {
            if (listener != null) {
                listener.progressDialogCancelled();
            }
        });

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (listener != null) {
                    listener.progressDialogCancelled();
                }
            }
        });

        pack();

        setLocationRelativeTo(parent);
    }

    public void setListener(ProgressDialogListener listener) {
        this.listener = listener;
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
