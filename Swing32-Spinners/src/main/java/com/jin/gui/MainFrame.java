package com.jin.gui;

import com.jin.controller.Controller;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final Controller controller;
    private final TablePanel tablePanel;
    private final PrefsDialog prefsDialog;

    public MainFrame() {
        super("Hello World");

        this.setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        prefsDialog = new PrefsDialog(this);
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        controller = new Controller();

        tablePanel.setData(controller.getPeople());
        tablePanel.setPersonTableListener(controller::removePerson);

        this.setJMenuBar(createMenuBar());

        toolbar.setTextListener(textPanel::appendText);

        formPanel.setFormListener(e -> {
            controller.addPerson(e);
            tablePanel.refresh();
        });

        this.add(formPanel, BorderLayout.WEST);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);

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
        JMenuItem prefsItem = new JMenuItem("Preferences...");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        prefsItem.addActionListener(e -> prefsDialog.setVisible(true));

        showFormItem.addActionListener(e -> {
            JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
            formPanel.setVisible(menuItem.isSelected());
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // set up accelerator
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));

        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));

        importDataItem.addActionListener(e -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.loadFromFile(fileChooser.getSelectedFile());
                    tablePanel.refresh();
                } catch (IOException | ClassNotFoundException ioException) {
                    JOptionPane.showMessageDialog(this,
                            "Could not load data from file.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exportDataItem.addActionListener(e -> {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.saveToFile(fileChooser.getSelectedFile());
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this,
                            "Could not save data to file.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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
