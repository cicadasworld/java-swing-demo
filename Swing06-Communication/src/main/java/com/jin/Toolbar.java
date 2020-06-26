package com.jin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener {

    private final JButton helloBtn;
    private final JButton goodbyeBtn;
    private TextPanel textPanel;

    public Toolbar() {
        helloBtn = new JButton("Hello");
        goodbyeBtn = new JButton("Goodbye");

        helloBtn.addActionListener(this);
        goodbyeBtn.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(helloBtn);
        this.add(goodbyeBtn);
    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == helloBtn) {
            this.textPanel.appendText("Hello\n");
        } else if (clicked == goodbyeBtn) {
            this.textPanel.appendText("Goodbye\n");
        }
    }
}
