package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.MagazynController;
import org.example.jpa.controllers.UserController;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.entities.KlientEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.User;
import org.example.jpa.repositories.KinoRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.MagazynViews.AddMagazynView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AddPracownikView extends BaseView {
    KinoEntity kino;

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

    JLabel cinemaText = new JLabel("Nazwa kina");
    JButton cinemaSelect = new JButton("Wybierz Kino");

    JLabel nameOfCinema = new JLabel("");

    JTextField jobType;
    JLabel jobTypeText;

    JCheckBox checkBox = new JCheckBox("Czy kierownik?");

    JButton accept = new JButton("Zatwierdź");
    JButton exit = new JButton("Wstecz");

    public AddPracownikView() {
        super(new GridLayout(0,2));

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

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

        jobType = new JTextField();
        jobTypeText = new JLabel("Rodzaj umowy");


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

        this.add(jobTypeText, BaseViewConstraint.CENTER);
        this.add(jobType, BaseViewConstraint.CENTER);

        this.add(cinemaText, BaseViewConstraint.CENTER);
        this.add(cinemaSelect, BaseViewConstraint.CENTER);

        this.add(new JPanel(), BaseViewConstraint.CENTER);
        this.add(nameOfCinema, BaseViewConstraint.CENTER);

        this.add(new JLabel(""), BaseViewConstraint.CENTER);
        this.add(checkBox, BaseViewConstraint.CENTER);

setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);
        this.add(accept,BaseViewConstraint.FOOTER);


        this.add(exit,BaseViewConstraint.FOOTER);

        cinemaSelect.addActionListener(new cinemaSelectedActionListener(this));
    }

    private class cinemaSelectedActionListener implements ActionListener {
        AddPracownikView addPracownikView;
        public cinemaSelectedActionListener(AddPracownikView addPracownikView) {
            this.addPracownikView = addPracownikView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog jDialog =new JDialog(frame,"Wybierz Kino z listy");
            JPanel panelContainer = new JPanel(new BorderLayout());

            JPanel panel = new JPanel(new GridLayout(0,1));

            JPanel borderLayout = new JPanel(new BorderLayout());
            borderLayout.add(panel,BorderLayout.NORTH);


            JScrollPane jScrollPane = new JScrollPane(borderLayout);
            jScrollPane.getVerticalScrollBar().setUnitIncrement(16);


            panelContainer.add(jScrollPane ,BorderLayout.CENTER);


//            JButton submit = new JButton("Ok");
            JButton cancel = new JButton("Anuluj");


            ///////
            List<KinoEntity> kinoEntities = KinoRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getAll().stream().sorted(Comparator.comparing(KinoEntity::getName)).collect(Collectors.toList());

            for (KinoEntity kinoEntity : kinoEntities) {

                JButton temp = new JButton(kinoEntity.getName());
                panel.add(temp);

                temp.addActionListener(new tempActionListener(addPracownikView,kinoEntity, jDialog));
            }


            JPanel buttons = new JPanel(new GridLayout(1,1));

            buttons.add(cancel);
            panelContainer.add(buttons,BorderLayout.SOUTH);

            jScrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            jDialog.add(panelContainer);

            jDialog.setVisible(true);
            jDialog.requestFocus();
            jDialog.setSize(280,200);
            jDialog.setLocationRelativeTo(null);


            cancel.addActionListener(e1 -> { jDialog.setVisible(false);});
        }
    }

    class tempActionListener implements ActionListener{
        KinoEntity kinoEntity;
        JDialog jDialog;
        AddPracownikView addPracownikView;
        tempActionListener(AddPracownikView addPracownikView,KinoEntity kinoEntity,JDialog jDialog){
            this.addPracownikView =addPracownikView;
            this.kinoEntity = kinoEntity;
            this.jDialog = jDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            jDialog.setVisible(false);

            UserController.getInstance(frame).createRepaint(kinoEntity, addPracownikView);
        }
    }
}
