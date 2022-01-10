package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.ui.views.BaseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter
@Setter
public class RegisterView extends BaseView {

    JTextField login = new JTextField();
    JLabel loginText = new JLabel("Login");

    JTextField password = new JTextField();
    JLabel passwordText = new JLabel("Hasło");

    JTextField firstName = new JTextField();
    JLabel firstNameText = new JLabel("Imię");

    JTextField surname = new JTextField();
    JLabel surnameText = new JLabel("Nazwisko");

    JTextField email = new JTextField();
    JLabel emailText = new JLabel("Email");

    JTextField phone = new JTextField();
    JLabel phoneText = new JLabel("Nr Telefonu");

    JTextField address = new JTextField();
    JLabel addressText = new JLabel("Adres");

    JButton signUp = new JButton("Zarejestruj");
    JButton exit = new JButton("Wstecz");

    public RegisterView() {
        super(new GridLayout(0,2));

        this.add(loginText);
        this.add(login);

        this.add(passwordText);
        this.add(password);

        this.add(firstNameText);
        this.add(firstName);

        this.add(surnameText);
        this.add(surname);

        this.add(emailText);
        this.add(email);

        this.add(phoneText);
        this.add(phone);

        this.add(addressText);
        this.add(address);

        this.add(exit);
        this.add(signUp);
    }


    public void addSignUpListener(ActionListener actionListener) {
        signUp.addActionListener(actionListener);
    }

    public void addExitListener(ActionListener actionListener) {
        exit.addActionListener(actionListener);
    }
}
