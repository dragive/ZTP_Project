package org.example.ui.Frames;

import org.checkerframework.checker.units.qual.K;
import org.example.jpa.controllers.UserController;
import org.example.jpa.entities.PracownikEntity;
import org.example.ui.Colors.BackgroundColor;
import org.example.ui.views.UserViews.KlientLoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class MainFrame extends JFrame {
    public MainFrame() {
        setup();

        UserController userController = UserController.getInstance(this);
        userController.login();
    }

    private void setup() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(720,445));
        this.setMinimumSize(new Dimension(720,445));
        this.setFocusable(true);

        //WYKORZYSTANIE FACTORY METHOD
        BackgroundColor backgroundColor = chooseColor(this);
        backgroundColor.pickBackgroundColor();

        //this.getContentPane().setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setTitle("Czesio's Cinema Manager");
    }

    public abstract BackgroundColor chooseColor(JFrame frame);
}