package com.jin;

import java.awt.*;

import javax.swing.*;

public class TextPanel extends JPanel {

    private final JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text);
    }
}
