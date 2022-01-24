package org.example.ui.Colors;

import javax.swing.*;
import java.awt.*;

public class MacOSColor implements BackgroundColor {
    JFrame frame;
    Color color;

    public MacOSColor(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void pickBackgroundColor() {
        color = new Color(211, 211, 211);
        frame.setBackground(color);
    }
}
