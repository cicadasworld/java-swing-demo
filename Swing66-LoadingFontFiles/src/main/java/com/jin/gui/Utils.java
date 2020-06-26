package com.jin.gui;

import java.awt.*;
import java.io.IOException;
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

    public static Font createFont(String path) {
        URL url = System.class.getResource(path);
        if (url == null) {
            System.err.println("Unable to load font: " + path);
        }
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
        } catch (FontFormatException e) {
            System.err.println("Bad format in font file: " + path);
        } catch (IOException e) {
            System.err.println("Unable to read font file: " + path);
        }
        return font;
    }
}
