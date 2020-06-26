package com.jin.gui;

import java.awt.*;

import javax.swing.*;

public class TextPanel extends JPanel {

    private final JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();

        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // not stick to the edge

        textArea.setFont(new Font("Serif", Font.PLAIN, 20));

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text);
    }

    public void setText(String text) {
        textArea.setText(text);
    }
}
