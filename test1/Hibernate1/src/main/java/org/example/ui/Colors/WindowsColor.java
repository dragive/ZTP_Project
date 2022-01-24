package org.example.ui.Colors;

import javax.swing.*;
import java.awt.*;

public class WindowsColor implements BackgroundColor {
    JFrame frame;
    Color color;

    public WindowsColor(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void pickBackgroundColor() {
        color = new Color(173, 216, 230);
        frame.getContentPane().setBackground(color);
    }
}
