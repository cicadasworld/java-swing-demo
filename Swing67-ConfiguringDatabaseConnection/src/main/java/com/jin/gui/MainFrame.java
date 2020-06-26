package com.jin.gui;

import com.jin.controller.Controller;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final Controller controller;
    private final TablePanel tablePanel;
    private final PrefsDialog prefsDialog;
    private final Preferences prefs;
    private final JSplitPane splitPane;
    private final JTabbedPane tabbedPane;
    private final MessagePanel messagePanel;

    public MainFrame() {
        super("Hello World");

        this.setLayout(new BorderLayout());

        toolbar = new Toolbar();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        prefsDialog = new PrefsDialog(this);
        tabbedPane = new JTabbedPane();
        messagePanel = new MessagePanel(this);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabbedPane);
        splitPane.setOneTouchExpandable(true);

        tabbedPane.addTab("Person Database", tablePanel);
        tabbedPane.addTab("Messages", messagePanel);

        prefs = Preferences.userRoot().node("db");

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        controller = new Controller();

        tablePanel.setData(controller.getPeople());
        tablePanel.setPersonTableListener(controller::removePerson);

        tabbedPane.addChangeListener(e -> {
            int tabIndex = tabbedPane.getSelectedIndex();
            if (tabIndex == 1) {
                messagePanel.refresh();
            }
        });

        prefsDialog.setPrefsListener((user, password, port) -> {
            prefs.put("user", user);
            prefs.put("password", password);
            prefs.putInt("port", port);

            try {
                controller.configure(user, password, port);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Unable to re-connect to database.",
                        "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
            }
        });

        String user = prefs.get("user", "");
        String password = prefs.get("password", "");
        int port = prefs.getInt("port", 3306);
        prefsDialog.setDefaults(user, password, port);

        try {
            controller.configure(user, password, port);
        } catch (Exception e) {
            System.err.println("Can't connect to database");
        }

        this.setJMenuBar(createMenuBar());

        toolbar.setToolbarListener(new ToolbarListener() {
            @Override
            public void saveEventOccurred() {
                connect();
                try {
                    controller.save();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Unable to save to database.",
                            "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void refreshEventOccurred() {
                connect();
                try {
                    controller.load();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Unable to load from database.",
                            "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
                }
                tablePanel.refresh();
            }
        });

        formPanel.setFormListener(e -> {
            controller.addPerson(e);
            tablePanel.refresh();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.disconnect();
                dispose();
                System.gc();
            }
        });

        this.add(toolbar, BorderLayout.PAGE_START);
        this.add(splitPane, BorderLayout.CENTER);

        this.setMinimumSize(new Dimension(500, 400));
        this.setSize(600, 500);
        this.setLocationRelativeTo(null); // To center
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

    private void connect() {
        try {
            controller.connect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Cannot connect to database.",
                    "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
        }
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
            if (menuItem.isSelected()) {
                splitPane.setDividerLocation(formPanel.getMinimumSize().width);
            }
            formPanel.setVisible(menuItem.isSelected());
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // set up accelerator
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

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
            int action = JOptionPane.showConfirmDialog(this,
                    "Do you really want o exit the application?",
                    "Confirm Exit",
                    JOptionPane.OK_CANCEL_OPTION);
            if (action == JOptionPane.OK_OPTION) {
                WindowListener[] listeners = getWindowListeners();
                for (WindowListener listener : listeners) {
                    listener.windowClosing(new WindowEvent(this, JFrame.DO_NOTHING_ON_CLOSE));
                }
            }
        });

        return menuBar;
    }
}
