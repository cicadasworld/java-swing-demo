package com.jin;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final Toolbar toolbar;
    private final FormPanel formPanel;

    public MainFrame() {
        super("Hello World");

        this.setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();

        toolbar.setTextListener(textPanel::appendText);

        formPanel.setFormListener(e -> {
            String name = e.getName();
            String occupation = e.getOccupation();
            int ageCat = e.getAgeCategory();
            String empCat = e.getEmpCategory();
            textPanel.appendText(name + ": " + occupation + ": " + ageCat + ", " + empCat + "\n");
            System.out.println(e.getGender());
        });

        this.add(formPanel, BorderLayout.WEST);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(textPanel, BorderLayout.CENTER);

        this.setSize(600, 500);
        this.setLocationRelativeTo(null); // To center
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
