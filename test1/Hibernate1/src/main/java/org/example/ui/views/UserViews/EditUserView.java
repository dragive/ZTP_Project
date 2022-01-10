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
public class EditUserView extends BaseView {

    User panelUser;

    Long id;

    KlientEntity klientEntity;
    PracownikEntity pracownikEntity;

    JTextField firstName;
    JLabel firstNameText;

    JTextField surname;
    JLabel surnameText;

    JTextField login;
    JLabel loginText;

    JTextField email;
    JLabel emailText;

    JTextField phone;
    JLabel phoneText;

    JTextField address;
    JLabel addressText;

    JTextField jobType;
    JLabel jobTypeText;

    JLabel isBossText;

    JButton delete = new JButton("Usuń konto");
    JButton accept = new JButton("Zatwierdź");
    JButton exit = new JButton("Wstecz");

    public EditUserView(User panelUser) {
        super(new GridLayout(0,2));
        this.panelUser = panelUser;

        if(panelUser instanceof KlientEntity) {
            klientEntity = (KlientEntity) panelUser;
            id = klientEntity.getId();
        }
        else {
            pracownikEntity = (PracownikEntity) panelUser;
            id = pracownikEntity.getId();
        }

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);
        if(panelUser instanceof KlientEntity) {
            firstName = new JTextField(klientEntity.getImie());
            firstNameText = new JLabel("Imię");

            surname = new JTextField(klientEntity.getNazwisko());
            surnameText = new JLabel("Nazwisko");

            login = new JTextField(klientEntity.getLogin());
            loginText = new JLabel("Nazwa użytkownika");

            email = new JTextField(klientEntity.getEmail());
            emailText = new JLabel("Adres e-mail");

            phone = new JTextField(klientEntity.getNrTelefonu());
            phoneText = new JLabel("Numer telefonu");

            address = new JTextField(klientEntity.getAdres());
            addressText = new JLabel("Adres");

            this.add(firstNameText,BaseViewConstraint.CENTER);
            this.add(firstName,BaseViewConstraint.CENTER);

            this.add(surnameText,BaseViewConstraint.CENTER);
            this.add(surname,BaseViewConstraint.CENTER);

            this.add(loginText,BaseViewConstraint.CENTER);
            this.add(login,BaseViewConstraint.CENTER);

            this.add(emailText,BaseViewConstraint.CENTER);
            this.add(email,BaseViewConstraint.CENTER);

            this.add(phoneText,BaseViewConstraint.CENTER);
            this.add(phone,BaseViewConstraint.CENTER);

            this.add(addressText,BaseViewConstraint.CENTER);
            this.add(address,BaseViewConstraint.CENTER);
        }
        else {
            firstName = new JTextField(pracownikEntity.getImie());
            firstNameText = new JLabel("Imię");

            surname = new JTextField(pracownikEntity.getNazwisko());
            surnameText = new JLabel("Nazwisko");

            login = new JTextField(pracownikEntity.getLogin());
            loginText = new JLabel("Nazwa użytkownika");

            email = new JTextField(pracownikEntity.getEmail());
            emailText = new JLabel("Adres e-mail");

            phone = new JTextField(pracownikEntity.getNrTelefonu());
            phoneText = new JLabel("Numer telefonu");

            jobType = new JTextField(pracownikEntity.getRodzajUmowy());
            jobTypeText = new JLabel("Rodzaj umowy");

            if(pracownikEntity.getCzyKierownik()){
                isBossText = new JLabel("Kierownik");
            }
            else isBossText = new JLabel("Pracownik");

            this.add(firstNameText,BaseViewConstraint.CENTER);
            this.add(firstName,BaseViewConstraint.CENTER);

            this.add(surnameText,BaseViewConstraint.CENTER);
            this.add(surname,BaseViewConstraint.CENTER);

            this.add(loginText,BaseViewConstraint.CENTER);
            this.add(login,BaseViewConstraint.CENTER);

            this.add(emailText,BaseViewConstraint.CENTER);
            this.add(email,BaseViewConstraint.CENTER);

            this.add(phoneText,BaseViewConstraint.CENTER);
            this.add(phone,BaseViewConstraint.CENTER);

            this.add(jobTypeText,BaseViewConstraint.CENTER);
            this.add(jobType,BaseViewConstraint.CENTER);

            this.add(new JLabel(""),BaseViewConstraint.CENTER);
            this.add(isBossText,BaseViewConstraint.CENTER);
        }


        setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);
        this.add(accept,BaseViewConstraint.FOOTER);

        this.add(delete,BaseViewConstraint.FOOTER);

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
