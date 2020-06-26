package com.jin.gui;

import java.awt.*;

import javax.swing.*;

public class ProgressDialog extends JDialog {

    public ProgressDialog(Window parent) {
        super(parent, "Messages Downloading...", ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setSize(400, 200);
    }
}
