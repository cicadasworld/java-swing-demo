package com.jin;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;

    public MainFrame() {
        super("Hello World");

        this.setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        fileChooser = new JFileChooser();

        this.setJMenuBar(createMenuBar());

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

        this.setMinimumSize(new Dimension(500, 400));
        this.setSize(600, 500);
        this.setLocationRelativeTo(null); // To center
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(e -> {
            JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
            formPanel.setVisible(menuItem.isSelected());
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // set up accelerator
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));

        importDataItem.addActionListener(e -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                System.out.println(fileChooser.getSelectedFile());
            }
        });

        exportDataItem.addActionListener(e -> {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                System.out.println(fileChooser.getSelectedFile());
            }
        });

        exitItem.addActionListener(e -> {
//            String text = JOptionPane.showInputDialog(this,
//                    "Enter your user name.",
//                    "Enter User Name",
//                    JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE);
//
//            System.out.println(text);

            int action = JOptionPane.showConfirmDialog(this,
                    "Do you really want o exit the application?",
                    "Confirm Exit",
                    JOptionPane.OK_CANCEL_OPTION);
            if (action == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });

        return menuBar;
    }
}
