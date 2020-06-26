package com.jin.gui;

import com.jin.model.Message;

import java.awt.*;

import javax.swing.*;

/*
 *  Note -- this demonstrates using a arbitrary component as a list box renderer.
 *  (Probably overkill in this case to use JPanel when JLabel could be used directly)
 */
public class MessageListRenderer implements ListCellRenderer<Message> {

    private final JPanel panel;
    private final JLabel label;

    private Color selectedColor;
    private Color normalColor;

    public MessageListRenderer() {
        panel = new JPanel();
        label = new JLabel();

        selectedColor = new Color(210, 210, 255);
        normalColor = Color.WHITE;

        label.setIcon(Utils.createIcon("/images/Information24.gif"));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(label);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Message message, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        label.setText(message.getTitle());
        panel.setBackground(cellHasFocus ? selectedColor : normalColor);
        return panel;
    }
}
