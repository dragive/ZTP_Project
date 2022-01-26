package org.example.ui.Colors;

import javax.swing.*;
import java.awt.*;

public class LinuxColor implements BackgroundColor {
    JFrame frame;
    Color color;

    public LinuxColor(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void pickBackgroundColor() {
        color = new Color(254,216,177);
        frame.getContentPane().setBackground(color);
    }
}
