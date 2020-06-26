package com.jin.gui;

import java.net.URL;

import javax.swing.*;

public class Utils {

    public static String getFileExtension(String name) {
        int pointIndex = name.lastIndexOf(".");

        if (pointIndex == -1) {
            return null;
        }

        if (pointIndex == name.length() - 1) {
            return null;
        }

        return name.substring(pointIndex + 1);
    }


    public static ImageIcon createIcon(String path) {
        URL url = System.class.getResource(path);
        if (url == null) {
            System.err.println("Unable to load image: " + path);
        }
        return new ImageIcon(url);
    }
}
