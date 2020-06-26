package com.jin;

import java.awt.*;

import javax.swing.*;

public class FormPanel extends JPanel {
    public FormPanel() {
        Dimension dim = this.getPreferredSize();
        dim.width = 250;
        this.setPreferredSize(dim);
    }
}
