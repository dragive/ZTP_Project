package org.example.jpa.controllers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.entities.*;
import org.example.jpa.repositories.KlientRepository;
import org.example.jpa.repositories.PracownikRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.ErrorFrame;
import org.example.ui.views.MenuPanel;
import org.example.ui.views.PopUps;
import org.example.ui.views.UserViews.*;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserController{
    //repositories
    KlientRepository klientRepository;
    PracownikRepository pracownikRepository;
    private static UserController instance;

    //views
    KlientLoginView klientLoginView;
    RegisterView registerView;
    PracownikLoginView pracownikLoginView;
    UserView userView;
    UserListView userListView;
    EditUserView editUserView;
    KlientOrPracownikView klientOrPracownikView;
    AddKlientView addKlientView;
    AddPracownikView addPracownikView;
    KlientReservations klientReservations;
    KlientReservation klientReservation;

    //models
    KlientEntity klientEntity;
    PracownikEntity pracownikEntity;

    //frame
    JFrame frame;

    private UserController(JFrame frame){
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        klientRepository = KlientRepository.builder().sessionFactory(sessionFactory).build();
        pracownikRepository = PracownikRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static UserController getInstance(JFrame frame) {
        if(instance==null) instance = new UserController(frame);
        return instance;
    }

    public void login() {
        klientLoginView = new KlientLoginView();
        frame.add(klientLoginView, BorderLayout.PAGE_START);
        frame.revalidate();
        frame.repaint();
        klientLoginView.requestFocus();

        klientLoginView.addKlientSignInListener(new KlientSignInListener());
        klientLoginView.addExitListener(new KlientExitListener());
        klientLoginView.addSwitchToPracownikLoginViewListener(new KlientSwitchToPracownikLoginViewListener());
        klientLoginView.addKlientSignUpListener(new KlientSignUpListener());
    }

    public void signUp() {
        frame.remove(klientLoginView);
        registerView = new RegisterView();
        frame.add(registerView, BorderLayout.PAGE_START);
        frame.revalidate();
        frame.repaint();
        registerView.requestFocus();

        registerView.addSignUpListener(new SignUpButtonListener());
        registerView.addExitListener(new ExitButtonListener());
    }

    public void pracownikLogin() {
        frame.remove(klientLoginView);
        pracownikLoginView = new PracownikLoginView();
        frame.add(pracownikLoginView, BorderLayout.PAGE_START);
        frame.revalidate();
        frame.repaint();
        pracownikLoginView.requestFocus();

        pracownikLoginView.addSignInListener(new PracownikSignInListener());
        pracownikLoginView.addExitListener(new KlientExitListener());
        pracownikLoginView.addSwitchToKlientLoginViewListener(new PracownikSwitchToKlientLoginViewListener());
    }

    public void details(User panelUser) {
        userView = new UserView(panelUser);
        userView.getExit().addActionListener(new detailsExitListener());
        userView.getEdit().addActionListener(new detailsEditListener());
        userView.getReservationTransactions().addActionListener(new detailsReservationTransactionsListener(panelUser));
        userView.getReservations().addActionListener(new detailsReservationsListener(panelUser));
        frame.add(userView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        userView.requestFocus();
    }

    public void list() {
        List<KlientEntity> klientEntities = klientRepository.getAll();
        List<PracownikEntity> pracownikEntities = pracownikRepository.getAll();

        List<User> users = new ArrayList<>();

        users.addAll(pracownikEntities);
        users.addAll(klientEntities);

        userListView = new UserListView(users);
        frame.add(userListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();

        userListView.getAddUser().addActionListener(new listAddUserListener());
    }

    public void klientOrPracownik() {
        klientOrPracownikView = new KlientOrPracownikView();
        frame.add(klientOrPracownikView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        klientOrPracownikView.requestFocus();

        klientOrPracownikView.getKlient().addActionListener(new klientOrPracownikKlientListener());
        klientOrPracownikView.getPracownik().addActionListener(new klientOrPracownikPracownikListener());
        klientOrPracownikView.getExit().addActionListener(new klientOrPracownikExitListener());
    }

    public void addKlient() {
        addKlientView = new AddKlientView();
        frame.add(addKlientView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addKlientView.requestFocus();

        addKlientView.getAccept().addActionListener(new addKlientAcceptListener());
        addKlientView.getExit().addActionListener(new addKlientExitListener());
    }

    public void addPracownik() {
        addPracownikView = new AddPracownikView();
        frame.add(addPracownikView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addPracownikView.requestFocus();

        addPracownikView.getAccept().setEnabled(false);
        addPracownikView.getExit().addActionListener(new addPracownikExitListener());
    }


    public void edit(User panelUser) {
        editUserView = new EditUserView(panelUser);
        frame.add(editUserView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        editUserView.requestFocus();

        editUserView.getAccept().addActionListener(new editAcceptListener());
        editUserView.getExit().addActionListener(new editExitListener());
        editUserView.getDelete().addActionListener(new editDeleteListener());
    }

    public void createRepaint(KinoEntity kino, AddPracownikView addPracownikViewOld) {
        this.addPracownikView = new AddPracownikView();
        addPracownikView.getEmail().setText(addPracownikViewOld.getEmail().getText());
        addPracownikView.getLogin().setText(addPracownikViewOld.getLogin().getText());
        addPracownikView.getFirstName().setText(addPracownikViewOld.getFirstName().getText());
        addPracownikView.getJobType().setText(addPracownikViewOld.getJobType().getText());
        addPracownikView.getPassword().setText(addPracownikViewOld.getPassword().getText());
        addPracownikView.getPhone().setText(addPracownikViewOld.getPhone().getText());
        addPracownikView.getCheckBox().setSelected(addPracownikViewOld.getCheckBox().isSelected());
        addPracownikView.getSurname().setText(addPracownikViewOld.getSurname().getText());

        frame.remove(addPracownikViewOld);
        frame.add(addPracownikView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addPracownikView.requestFocus();

        addPracownikView.getAccept().addActionListener(new createAcceptListener(kino));
        addPracownikView.getExit().addActionListener(new createExitListener());

        addPracownikView.getNameOfCinema().setText(kino.getName());
        addPracownikView.setKino(kino);

    }

    public void reservations(User panelUser) {
        KlientEntity klient = (KlientEntity) panelUser;

        List<RezerwacjaEntity> rezerwacjaEntityList = klient.getRezerwacje();
        List<JButton> buttonList = new ArrayList<>();
        for(RezerwacjaEntity rezerwacja: rezerwacjaEntityList) {
            JButton temp = new JButton(rezerwacja.getNazwa());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.remove(klientReservations);
                    reservation(panelUser,klientReservations,rezerwacja);
                }
            });
            buttonList.add(temp);
        }

        klientReservations = new KlientReservations(buttonList);
        frame.add(klientReservations, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        klientReservations.requestFocus();

        klientReservations.getExit().addActionListener(new reservationsExitListener(panelUser));
    }

    public void reservation(User panelUser, Object previousWindow, RezerwacjaEntity rezerwacja) {
        klientReservation = new KlientReservation(rezerwacja);
        frame.add(klientReservation, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        klientReservation.requestFocus();

        klientReservation.getExit().addActionListener(new reservationExitListener(panelUser,previousWindow));
        klientReservation.getPayment().addActionListener(new reservationPaymentListener(rezerwacja));
    }


    //login

    private class KlientSignInListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                klientEntity = klientRepository.getByLogin(klientLoginView.getLogin().getText());
                if(klientEntity!=null)
                {
                    if(klientEntity.getLogin().equals(klientLoginView.getLogin().getText()) && klientEntity.getHaslo().equals(klientLoginView.getPassword().getText())) {
                        frame.remove(klientLoginView);
                        MenuPanel menuPanel = new MenuPanel(frame,klientEntity);
                        frame.add(menuPanel, BorderLayout.PAGE_START);
                        CinemaController cinemaController = CinemaController.getInstance(frame);
                        cinemaController.index();
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            } catch (Exception ex) {
                new ErrorFrame("Nieprawidłowy login lub hasło!");
            }
        }
    }

    private class KlientExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    private class KlientSignUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            signUp();
        }
    }

    private class KlientSwitchToPracownikLoginViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pracownikLogin();
        }
    }

    //signUp

    private class SignUpButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            KlientEntity newKlient = new KlientEntity();
            try {
                newKlient.setId(klientRepository.getNewId());
                newKlient.setLogin(registerView.getLogin().getText());
                newKlient.setHaslo(registerView.getPassword().getText());
                newKlient.setImie(registerView.getFirstName().getText());
                newKlient.setNazwisko(registerView.getSurname().getText());
                newKlient.setNrTelefonu(registerView.getPhone().getText());
                newKlient.setEmail(registerView.getEmail().getText());
                newKlient.setAdres(registerView.getAddress().getText());

                validateUserEntity(newKlient);

                klientRepository.save(newKlient);
            }
            catch (Exception ex){
                PopUps.Error("Wystąpił błąd. Sprawdź czy wprowadzone dane są poprawne!");
            }

            frame.remove(registerView);
            MenuPanel menuPanel = new MenuPanel(frame,newKlient);
            frame.add(menuPanel, BorderLayout.PAGE_START);
            CinemaController cinemaController = CinemaController.getInstance(frame);
            cinemaController.index();
            frame.revalidate();
            frame.repaint();
        }
    }

    private class ExitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(registerView);
            login();
        }
    }


    //PracownikLogin

    private class PracownikSwitchToKlientLoginViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(pracownikLoginView);
            login();
        }
    }

    private class PracownikSignInListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                pracownikEntity = pracownikRepository.getByLogin(pracownikLoginView.getLogin().getText());
                if(pracownikEntity!=null)
                {
                    if(pracownikEntity.getLogin().equals(pracownikLoginView.getLogin().getText()) && pracownikEntity.getHaslo().equals(pracownikLoginView.getPassword().getText())) {
                        frame.remove(pracownikLoginView);
                        MenuPanel menuPanel = new MenuPanel(frame,pracownikEntity);
                        frame.add(menuPanel, BorderLayout.PAGE_START);
                        CinemaController cinemaController = CinemaController.getInstance(frame);
                        cinemaController.index();
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            } catch (Exception ex) {
                new ErrorFrame("Nieprawidłowy login lub hasło!");
            }

        }
    }

    //details

    private class detailsExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(userView);
            list();
        }
    }

    private class detailsEditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(userView);
            edit(userView.getPanelUser());
        }
    }

    private class editExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(editUserView);
            details(editUserView.getPanelUser());
        }
    }

    private class editAcceptListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            KlientEntity updatedKlientEntity=null;
            PracownikEntity updatedPracownikEntity=null;
            User panelUser = editUserView.getPanelUser();

            try {
                if(panelUser instanceof KlientEntity) {
                    updatedKlientEntity =(KlientEntity) editUserView.getPanelUser();
                }
                else {
                    updatedPracownikEntity = (PracownikEntity) editUserView.getPanelUser();
                }

                if(panelUser instanceof KlientEntity) {
                    updatedKlientEntity.setLogin(editUserView.getLogin().getText());
                    updatedKlientEntity.setAdres(editUserView.getAddress().getText());
                    updatedKlientEntity.setEmail(editUserView.getEmail().getText());
                    updatedKlientEntity.setNrTelefonu(editUserView.getPhone().getText());
                    updatedKlientEntity.setImie(editUserView.getFirstName().getText());
                    updatedKlientEntity.setNazwisko(editUserView.getSurname().getText());

                    validateUserEntity(updatedKlientEntity);

                    klientRepository.update(updatedKlientEntity);

                }
                else {
                    updatedPracownikEntity.setLogin(editUserView.getLogin().getText());
                    updatedPracownikEntity.setEmail(editUserView.getEmail().getText());
                    updatedPracownikEntity.setNrTelefonu(editUserView.getPhone().getText());
                    updatedPracownikEntity.setImie(editUserView.getFirstName().getText());
                    updatedPracownikEntity.setNazwisko(editUserView.getSurname().getText());
                    updatedPracownikEntity.setRodzajUmowy(editUserView.getJobType().getText());

                    validateEmplEntity(updatedPracownikEntity);

                    pracownikRepository.update(updatedPracownikEntity);
                }
            } catch (Exception ex) {
                log.error(ex.toString());
                new ErrorFrame("Podaleś złe dane. Upewnij się że wszystko jest dobrze!");
//                throw ex;
            }

            frame.remove(editUserView);
            if(panelUser instanceof KlientEntity) {
                details(updatedKlientEntity);
            }
            else {
                details(updatedPracownikEntity);
            }
        }
    }

    private class editDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            KlientEntity deleteKlientEntity=null;
            PracownikEntity deletePracownikEntity=null;

            if(PopUps.OkCancel("Czy na pewno chcesz usunąć użytkownika?")){
                User panelUser = editUserView.getPanelUser();

                if (panelUser instanceof KlientEntity) {
                    deleteKlientEntity = (KlientEntity) panelUser;
                    klientRepository.delete(deleteKlientEntity);
                } else {
                    deletePracownikEntity = (PracownikEntity) panelUser;
                    pracownikRepository.delete(deletePracownikEntity);
                }

                frame.remove(editUserView);


                if (MenuPanel.user instanceof KlientEntity) {
                    frame.remove(MenuPanel.instance);
                    login();
                } else {
                    list();
                }
            }
        }
    }

    private class listAddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(userListView);
            klientOrPracownik();
        }
    }


    private class klientOrPracownikExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(klientOrPracownikView);
            list();
        }
    }

    private class klientOrPracownikKlientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(klientOrPracownikView);
            addKlient();
        }
    }

    private class klientOrPracownikPracownikListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(klientOrPracownikView);
            addPracownik();
        }
    }

    private class addKlientExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addKlientView);
            list();
        }
    }

    private class addKlientAcceptListener implements ActionListener {
        @SneakyThrows
        @Override
        public void actionPerformed(ActionEvent e) {
            KlientEntity klient = new KlientEntity();
            klient.setId(klientRepository.getNewId());
            klient.setAdres(addKlientView.getAddress().getText());
            klient.setImie(addKlientView.getFirstName().getText());
            klient.setEmail(addKlientView.getEmail().getText());
            klient.setNazwisko(addKlientView.getSurname().getText());
            klient.setLogin(addKlientView.getLogin().getText());
            klient.setHaslo(addKlientView.getPassword().getText());
            klient.setNrTelefonu(addKlientView.getPhone().getText());

            validateUserEntity(klient);

            klientRepository.save(klient);

            frame.remove(addKlientView);
            list();
        }
    }


    private class addPracownikExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addPracownikView);
            list();
        }
    }


    private class createExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addPracownikView);
            list();
        }
    }

    private class createAcceptListener implements ActionListener {
        KinoEntity kino;
        public createAcceptListener(KinoEntity kino) {
            this.kino = kino;
        }

        @SneakyThrows
        @Override
        public void actionPerformed(ActionEvent e) {
            PracownikEntity pracownik = new PracownikEntity();
            pracownik.setId(pracownikRepository.getNewId());
            pracownik.setImie(addPracownikView.getFirstName().getText());
            pracownik.setEmail(addPracownikView.getEmail().getText());
            pracownik.setNazwisko(addPracownikView.getSurname().getText());
            pracownik.setLogin(addPracownikView.getLogin().getText());
            pracownik.setHaslo(addPracownikView.getPassword().getText());
            pracownik.setRodzajUmowy(addPracownikView.getJobType().getText());
            pracownik.setNrTelefonu(addPracownikView.getPhone().getText());
            pracownik.setCzyKierownik(addPracownikView.getCheckBox().isSelected());
            pracownik.setKino(kino);

            validateEmplEntity(pracownik);

            pracownikRepository.save(pracownik);

            frame.remove(addPracownikView);
            list();
        }
    }

    private class reservationsExitListener implements ActionListener {
        User panelUser;
        public reservationsExitListener(User panelUser) {
            this.panelUser = panelUser;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(klientReservations);
            edit(panelUser);
        }
    }

    private class detailsReservationsListener implements ActionListener {
        User panelUser;
        public detailsReservationsListener(User panelUser) {
            this.panelUser = panelUser;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(userView);
            reservations(panelUser);
        }
    }

    private class reservationExitListener implements ActionListener {
        User panelUser;
        Object previousWindow;
        public reservationExitListener(User panelUser, Object previousWindow) {
            this.panelUser = panelUser;
            this.previousWindow = previousWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(klientReservation);
            details(panelUser);
        }
    }

    private class detailsReservationTransactionsListener implements ActionListener {
        private User panelUser;

        public detailsReservationTransactionsListener(User panelUser) {
            this.panelUser = panelUser;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(userView);
            ReservationTransactionController.getInstance(frame).list((KlientEntity) panelUser);
        }
    }

    private class reservationPaymentListener implements ActionListener {


        private RezerwacjaEntity rezerwacja;

        public reservationPaymentListener(RezerwacjaEntity rezerwacja) {
            this.rezerwacja = rezerwacja;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(klientReservation);
            ReservationTransactionController.getInstance(frame).create(rezerwacja,klientReservation);
        }
    }
    public void validateUserEntity(KlientEntity klientEntity) throws Exception{
        if(klientEntity.getLogin().equals("") || klientEntity.getHaslo().equals("") || klientEntity.getEmail().equals("") || klientEntity.getAdres().equals("") || klientEntity.getImie().equals("") || klientEntity.getNazwisko().equals("") || klientEntity.getNrTelefonu().equals("")){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }

    public void validateEmplEntity(PracownikEntity pracownikEntity) throws Exception{
        if(pracownikEntity.getImie().equals("") || pracownikEntity.getNazwisko().equals("") || pracownikEntity.getLogin().equals("") || pracownikEntity.getEmail().equals("") || pracownikEntity.getNrTelefonu().equals("") || pracownikEntity.getHaslo().equals("")){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }
}
