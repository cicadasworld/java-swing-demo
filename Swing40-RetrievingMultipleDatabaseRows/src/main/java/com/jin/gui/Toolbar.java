package com.jin.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener {

    private final JButton helloBtn;
    private final JButton goodbyeBtn;
    private TextListener textListener;

    public Toolbar() {
        this.setBorder(BorderFactory.createEtchedBorder());
        helloBtn = new JButton("Hello");
        goodbyeBtn = new JButton("Goodbye");

        helloBtn.addActionListener(this);
        goodbyeBtn.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(helloBtn);
        this.add(goodbyeBtn);
    }

    public void setTextListener(TextListener listener) {
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == helloBtn) {
            this.textListener.textEmitted("Hello\n");
        } else if (clicked == goodbyeBtn) {
            this.textListener.textEmitted("Goodbye\n");
        }
    }
}
