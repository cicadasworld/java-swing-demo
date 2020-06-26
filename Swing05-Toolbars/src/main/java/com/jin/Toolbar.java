package com.jin;

import java.awt.*;

import javax.swing.*;

public class Toolbar extends JPanel {

    private final JButton helloBtn;
    private final JButton goodbyeBtn;

    public Toolbar() {
        helloBtn = new JButton("Hello");
        goodbyeBtn = new JButton("Goodbye");

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(helloBtn);
        this.add(goodbyeBtn);
    }
}
