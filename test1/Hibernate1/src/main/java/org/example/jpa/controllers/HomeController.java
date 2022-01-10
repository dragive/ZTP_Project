package org.example.jpa.controllers;

import org.example.ui.views.HomeViews.CreditsView;

import javax.swing.*;
import java.awt.*;

public class HomeController {
    private static HomeController instance;

    //views
    CreditsView creditsView;

    JFrame frame;
    private HomeController(JFrame frame) {
        this.frame = frame;
    }

    public static HomeController getInstance(JFrame frame) {
        if(instance==null) instance = new HomeController(frame);
        return instance;
    }

    public void credits() {
        creditsView = new CreditsView();
        frame.add(creditsView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        creditsView.requestFocus();
    }
}
