package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.ui.views.BaseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter
@Setter
public class PracownikLoginView extends BaseView {
    private JTextField login = new JTextField();
    private JLabel loginText = new JLabel("Login");

    private JTextField password = new JTextField();
    private JLabel passwordText = new JLabel("Hasło");

    private JButton signIn = new JButton("Zaloguj");
    private JButton switchToKlientLoginView = new JButton("Panel Klienta");
    private JButton exit = new JButton("Wyjdź");

    public PracownikLoginView() {

        super(new GridLayout(0,2));

        this.add(loginText);
        this.add(login);
        this.add(passwordText);
        this.add(password);
        this.add(new JLabel(""));
        this.add(signIn);
        this.add(switchToKlientLoginView);
        this.add(exit);
    }

    public void addSignInListener(ActionListener actionListener) {
        signIn.addActionListener(actionListener);
    }

    public void addSwitchToKlientLoginViewListener(ActionListener actionListener) {
        switchToKlientLoginView.addActionListener(actionListener);
    }

    public void addExitListener(ActionListener actionListener) {
        exit.addActionListener(actionListener);
    }
}

