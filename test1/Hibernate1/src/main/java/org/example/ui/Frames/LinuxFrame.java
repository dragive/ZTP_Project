package org.example.ui.Frames;

import org.example.ui.Colors.BackgroundColor;
import org.example.ui.Colors.LinuxColor;

import javax.swing.*;

public class LinuxFrame extends MainFrame{
    @Override
    public BackgroundColor chooseColor(JFrame frame) {
        return new LinuxColor(frame);
    }
}
