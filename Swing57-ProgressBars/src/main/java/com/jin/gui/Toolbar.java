package com.jin.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class Toolbar extends JToolBar implements ActionListener {

    private final JButton saveBtn;
    private final JButton refreshBtn;
    private ToolbarListener toolbarListener;

    public Toolbar() {
        // Get rid of the border if you want the toolbar draggable.
        this.setBorder(BorderFactory.createEtchedBorder());
        //this.setFloatable(false);

        saveBtn = new JButton();
        saveBtn.setIcon(Utils.createIcon("/images/Save16.gif"));
        saveBtn.setToolTipText("Save");

        refreshBtn = new JButton();
        refreshBtn.setIcon(Utils.createIcon("/images/Refresh16.gif"));
        refreshBtn.setToolTipText("Refresh");

        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);

        this.add(saveBtn);
        //this.addSeparator();
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
