package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KlientEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.User;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddKlientView extends BaseView {
    JTextField firstName;
    JLabel firstNameText;

    JTextField surname;
    JLabel surnameText;

    JTextField login;
    JLabel loginText;

    JTextField password;
    JLabel passwordText;

    JTextField email;
    JLabel emailText;

    JTextField phone;
    JLabel phoneText;

    JTextField address;
    JLabel addressText;

    JButton accept = new JButton("Zatwierdź");
    JButton exit = new JButton("Wstecz");

    public AddKlientView() {
        super(new GridLayout(0,2));
        mountPanels();
        setLayout(new GridLayout(0,2),BaseViewConstraint.CENTER);

        firstName = new JTextField();
        firstNameText = new JLabel("Imię");

        surname = new JTextField();
        surnameText = new JLabel("Nazwisko");

        login = new JTextField();
        loginText = new JLabel("Nazwa użytkownika");

        password = new JTextField();
        passwordText = new JLabel("Hasło");

        email = new JTextField();
        emailText = new JLabel("Adres e-mail");

        phone = new JTextField();
        phoneText = new JLabel("Numer telefonu");

        address = new JTextField();
        addressText = new JLabel("Adres");

        this.add(firstNameText, BaseViewConstraint.CENTER);
        this.add(firstName, BaseViewConstraint.CENTER);

        this.add(surnameText, BaseViewConstraint.CENTER);
        this.add(surname, BaseViewConstraint.CENTER);

        this.add(loginText, BaseViewConstraint.CENTER);
        this.add(login, BaseViewConstraint.CENTER);

        this.add(passwordText, BaseViewConstraint.CENTER);
        this.add(password, BaseViewConstraint.CENTER);

        this.add(emailText, BaseViewConstraint.CENTER);
        this.add(email, BaseViewConstraint.CENTER);

        this.add(phoneText, BaseViewConstraint.CENTER);
        this.add(phone, BaseViewConstraint.CENTER);

        this.add(addressText, BaseViewConstraint.CENTER);
        this.add(address, BaseViewConstraint.CENTER);


        JPanel jPanel = new JPanel(new GridLayout(1,2));

        jPanel.add(accept);


        jPanel.add(exit);

        this.add(jPanel,BaseViewConstraint.FOOTER);
    }
}
