package org.example.ui.Frames;

import org.example.ui.Colors.BackgroundColor;
import org.example.ui.Colors.MacOSColor;

import javax.swing.*;

public class MacOSFrame extends MainFrame{
    @Override
    public BackgroundColor chooseColor(JFrame frame) {
        return new MacOSColor(frame);
    }
}
