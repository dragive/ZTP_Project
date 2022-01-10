package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KlientEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.User;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class UserView extends BaseView {
    User panelUser;

    KlientEntity klientEntity;
    PracownikEntity pracownikEntity;

    JLabel firstName;
    JLabel firstNameText;

    JLabel surname;
    JLabel surnameText;

    JLabel login;
    JLabel loginText;

    JLabel email;
    JLabel emailText;

    JLabel phone;
    JLabel phoneText;

    JLabel address;
    JLabel addressText;

    JLabel jobType;
    JLabel jobTypeText;

    JLabel isBossText;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    JLabel date;

    JButton reservationTransactions = new JButton("Transakcje za rezerwacje");
    JButton edit = new JButton("Edytuj dane");
    JButton reservations = new JButton("Rezerwacje");
    JButton exit = new JButton("Wróć do listy użytkowników");

    public UserView(User panelUser) {
        super(new GridLayout(0,2));
        this.panelUser = panelUser;

        mountPanels();


        if(panelUser instanceof KlientEntity) {
            klientEntity = (KlientEntity) panelUser;
        }
        else {
            pracownikEntity = (PracownikEntity) panelUser;
        }

        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);
        if(panelUser instanceof KlientEntity) {
            firstName = new JLabel(klientEntity.getImie());
            firstNameText = new JLabel("Imię");

            surname = new JLabel(klientEntity.getNazwisko());
            surnameText = new JLabel("Nazwisko");

            login = new JLabel(klientEntity.getLogin());
            loginText = new JLabel("Nazwa użytkownika");

            email = new JLabel(klientEntity.getEmail());
            emailText = new JLabel("Adres e-mail");

            phone = new JLabel(klientEntity.getNrTelefonu());
            phoneText = new JLabel("Numer telefonu");

            address = new JLabel(klientEntity.getAdres());
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
            firstName = new JLabel(pracownikEntity.getImie());
            firstNameText = new JLabel("Imię");

            surname = new JLabel(pracownikEntity.getNazwisko());
            surnameText = new JLabel("Nazwisko");

            login = new JLabel(pracownikEntity.getLogin());
            loginText = new JLabel("Nazwa użytkownika");

            email = new JLabel(pracownikEntity.getEmail());
            emailText = new JLabel("Adres e-mail");

            phone = new JLabel(pracownikEntity.getNrTelefonu());
            phoneText = new JLabel("Numer telefonu");

            jobType = new JLabel(pracownikEntity.getRodzajUmowy());
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


        if(panelUser instanceof KlientEntity) {
            setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);

            this.add(edit,BaseViewConstraint.FOOTER);

            this.add(reservations,BaseViewConstraint.FOOTER);

            this.add(reservationTransactions,BaseViewConstraint.FOOTER);
        }

        if(MenuPanel.user instanceof PracownikEntity) {

            setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

            this.add(edit,BaseViewConstraint.FOOTER);

            this.add(exit,BaseViewConstraint.FOOTER);
        }
    }
}
