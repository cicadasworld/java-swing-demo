package com.jin;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final JButton btn;
    private final TextPanel textPanel;

    public MainFrame() {
        super("Hello World");

        this.setLayout(new BorderLayout());

        btn = new JButton("Click Me!");
        textPanel = new TextPanel();

        btn.addActionListener(e -> {
            textPanel.appendText("Hello\n");
        });

        this.add(textPanel, BorderLayout.CENTER);
        this.add(btn, BorderLayout.SOUTH);

        this.setSize(600, 500);
        this.setLocationRelativeTo(null); // To center
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
