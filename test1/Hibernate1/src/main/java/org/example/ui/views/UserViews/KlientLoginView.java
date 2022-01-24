package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.ui.views.BaseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
@Getter
@Setter
public class KlientLoginView extends BaseView{
    private JTextField login = new JTextField();
    private JLabel loginText = new JLabel("Login");

    private JTextField password = new JTextField();
    private JLabel passwordText = new JLabel("Hasło");

    private JButton signIn = new JButton("Zaloguj");
    private JButton signUp = new JButton("Zarejestruj");
    private JButton switchToPracownikLoginView = new JButton("Panel Pracownika");
    private JButton exit = new JButton("Wyjdź");

    public KlientLoginView() {

        super(new GridLayout(0,2));

        this.add(loginText);
        this.add(login);
        this.add(passwordText);
        this.add(password);
        this.add(signUp);
        this.add(signIn);
        this.add(switchToPracownikLoginView);
        this.add(exit);
    }

    public void addKlientSignInListener(ActionListener actionListener) {
        signIn.addActionListener(actionListener);
    }

    public void addKlientSignUpListener(ActionListener actionListener) {
        signUp.addActionListener(actionListener);
    }

    public void addSwitchToPracownikLoginViewListener(ActionListener actionListener) {
        switchToPracownikLoginView.addActionListener(actionListener);
    }

    public void addExitListener(ActionListener actionListener) {
        exit.addActionListener(actionListener);
    }
}