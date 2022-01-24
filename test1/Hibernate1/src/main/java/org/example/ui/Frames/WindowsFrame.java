package org.example.ui.Frames;

import org.example.ui.Colors.BackgroundColor;
import org.example.ui.Colors.WindowsColor;

import javax.swing.*;

public class WindowsFrame extends MainFrame{
    @Override
    public BackgroundColor chooseColor(JFrame frame) {
        return new WindowsColor(frame);
    }
}
