package com.jin;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final JTextArea textArea;
    private final JButton btn;

    public MainFrame() {
        super("Hello World");

        this.setLayout(new BorderLayout());

        textArea = new JTextArea();
        btn = new JButton("Click Me!");

        this.add(textArea, BorderLayout.CENTER);
        this.add(btn, BorderLayout.SOUTH);

        this.setSize(600, 500);
        this.setLocationRelativeTo(null); // To center
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
