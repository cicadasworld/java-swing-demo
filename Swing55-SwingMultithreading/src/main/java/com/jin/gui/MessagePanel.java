package com.jin.gui;

import com.jin.controller.MessageServer;
import com.jin.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.awt.*;
import java.util.concurrent.ExecutionException;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class MessagePanel extends JPanel {

    private final JTree serverTree;
    private final ServerTreeCellRenderer treeCellRenderer;
    private final ServerTreeCellEditor treeCellEditor;

    private final Set<Integer> selectedServers;
    private final MessageServer messageServer;

    public MessagePanel() {
        messageServer = new MessageServer();
        selectedServers = new TreeSet<>();
        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);

        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);

        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo info = (ServerInfo) treeCellEditor.getCellEditorValue();
                System.out.println(info + ": " + info.getId() + ": " + info.isChecked());
                int serverId = info.getId();
                if (info.isChecked()) {
                    selectedServers.add(serverId);
                } else {
                    selectedServers.remove(serverId);
                }
                messageServer.setSelectedServers(selectedServers);

                retrieveMessages();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private void retrieveMessages() {

        System.out.println("Messages waiting: " + messageServer.getMessageCount());

        SwingWorker<List<Message>, Integer> worker = new SwingWorker<List<Message>, Integer>() {
            @Override
            protected void process(List<Integer> counts) {
                int retrieved = counts.get(counts.size() - 1);
                System.out.println("Got " + retrieved + " messages");
            }

            @Override
            protected void done() {
                try {
                    List<Message> retrievedMessages = get();
                    System.out.println("Retrieved " + retrievedMessages.size() + " messages.");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected List<Message> doInBackground() throws Exception {
                List<Message> retrievedMessages = new ArrayList<>();
                int count = 0;
                for (Message message : messageServer) {
                    System.out.println(message.getTitle());
                    retrievedMessages.add(message);
                    count += 1;
                    publish(count);
                }
                return retrievedMessages;
            }
        };

        worker.execute();
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");

        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(
                new ServerInfo("New York", 0, selectedServers.contains(0)));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(
                new ServerInfo("Boston", 1, selectedServers.contains(1)));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(
                new ServerInfo("Los Angeles", 2, selectedServers.contains(2)));
        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");

        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(
                new ServerInfo("London", 3, selectedServers.contains(3)));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(
                new ServerInfo("Edinburgh", 4, selectedServers.contains(4)));
        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
}
