package com.jin.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener {

    private final JButton saveBtn;
    private final JButton refreshBtn;
    private ToolbarListener toolbarListener;

    public Toolbar() {
        this.setBorder(BorderFactory.createEtchedBorder());
        saveBtn = new JButton("Save");
        refreshBtn = new JButton("Refresh");

        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(saveBtn);
        this.add(refreshBtn);
    }

    public void setToolbarListener(ToolbarListener listener) {
        this.toolbarListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == saveBtn) {
            this.toolbarListener.saveEventOccurred();
        } else if (clicked == refreshBtn) {
            this.toolbarListener.refreshEventOccurred();
        }
    }
}
