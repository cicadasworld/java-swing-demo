package com.jin.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

public class MessagePanel extends JPanel {

    private final JTree serverTree;
    private final DefaultTreeCellRenderer treeCellRenderer;

    public MessagePanel() {
        treeCellRenderer = new DefaultTreeCellRenderer();

        treeCellRenderer.setLeafIcon(Utils.createIcon("/images/Server16.gif"));
        treeCellRenderer.setOpenIcon(Utils.createIcon("/images/WebComponent16.gif"));
        treeCellRenderer.setClosedIcon(Utils.createIcon("/images/WebComponentAdd16.gif"));

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);

        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        serverTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
            Object userObject = node.getUserObject();
            System.out.println(userObject);
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");

        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2));
        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");

        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 3));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 4));
        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
}

class ServerInfo {
    String name;
    int id;

    ServerInfo(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
