package org.example.jpa.controllers;

import org.example.jpa.entities.KlientEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.RezerwacjaTransakcjaEntity;
import org.example.jpa.repositories.RezerwacjaTranzakcjaRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.MenuPanel;
import org.example.ui.views.TransactionViews.AddReservationTransactionView;
import org.example.ui.views.TransactionViews.ReservationTransactionListView;
import org.example.ui.views.TransactionViews.ReservationTransactionView;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReservationTransactionController {
    private static ReservationTransactionController instance;

    RezerwacjaTranzakcjaRepository rezerwacjaTranzakcjaRepository;

    AddReservationTransactionView addReservationTransactionView;
    ReservationTransactionView reservationTransactionView;
    ReservationTransactionListView reservationTransactionListView;

    JFrame frame;

    private ReservationTransactionController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        rezerwacjaTranzakcjaRepository = RezerwacjaTranzakcjaRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static ReservationTransactionController getInstance(JFrame frame) {
        if(instance==null) instance = new ReservationTransactionController(frame);
        return instance;
    }

    public void list(KlientEntity klient) {
        List<JButton> list = new ArrayList<>();
        List<RezerwacjaTransakcjaEntity> rezerwacjaTransakcjaEntities = klient.getRezerwacjeTransakcje();

        for(RezerwacjaTransakcjaEntity rezerwacjaTransakcja: rezerwacjaTransakcjaEntities) {
            JButton temp = new JButton("Transakcja - "+rezerwacjaTransakcja.getRezerwacja().getNazwa());

            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.remove(reservationTransactionListView);
                    details(rezerwacjaTransakcja);
                }
            });

            list.add(temp);
        }

        reservationTransactionListView = new ReservationTransactionListView(list);
        frame.add(reservationTransactionListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        reservationTransactionListView.requestFocus();

        reservationTransactionListView.getExit().addActionListener(new listExitListener(klient));
    }

    public void create(RezerwacjaEntity rezerwacja,Object previousWindow) {
        addReservationTransactionView = new AddReservationTransactionView(rezerwacja,previousWindow);
        frame.add(addReservationTransactionView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addReservationTransactionView.requestFocus();

        addReservationTransactionView.getAccept().addActionListener(new createAcceptListener(rezerwacja, previousWindow));
        addReservationTransactionView.getExit().addActionListener(new createExitListener(rezerwacja,previousWindow));
    }

    public void details(RezerwacjaTransakcjaEntity rezerwacjaTransakcja) {
        reservationTransactionView = new ReservationTransactionView(rezerwacjaTransakcja);
        frame.add(reservationTransactionView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        reservationTransactionView.requestFocus();

        reservationTransactionView.getExit().addActionListener(new detailsExitListener(rezerwacjaTransakcja.getKlient()));
    }

    //create

    private class createExitListener implements ActionListener {
        private RezerwacjaEntity rezerwacja;
        private Object previousWindow;

        public createExitListener(RezerwacjaEntity rezerwacja, Object previousWindow) {
            this.rezerwacja = rezerwacja;
            this.previousWindow = previousWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addReservationTransactionView);
            if(previousWindow.getClass().toString().contains("ReservationSeansView")) {
                ReservationController.getInstance(frame).seansList(rezerwacja.getSeans().getSala().getKino());
            }
            if(previousWindow.getClass().toString().contains("KlientReservation")) {
                UserController userController = UserController.getInstance(frame);
                userController.details(rezerwacja.getKlient());
            }
        }
    }

    private class createAcceptListener implements ActionListener {
        private RezerwacjaEntity rezerwacja;
        private Object previousWindow;

        public createAcceptListener(RezerwacjaEntity rezerwacja, Object previousWindow) {
            this.rezerwacja = rezerwacja;
            this.previousWindow = previousWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addReservationTransactionView);

            RezerwacjaTransakcjaEntity rezerwacjaTransakcja = new RezerwacjaTransakcjaEntity();

            rezerwacjaTransakcja.setId(rezerwacjaTranzakcjaRepository.getNewId());
            rezerwacjaTransakcja.setCena(Double.parseDouble(addReservationTransactionView.getPrice().getText()));
            if(addReservationTransactionView.getGotowka().isSelected()) {
                rezerwacjaTransakcja.setCzyGotowka(true);
                rezerwacjaTransakcja.setCzyKarta(false);
            }
            else {
                rezerwacjaTransakcja.setCzyGotowka(false);
                rezerwacjaTransakcja.setCzyKarta(true);
            }
            rezerwacjaTransakcja.setPracownik((PracownikEntity) MenuPanel.user);
            rezerwacjaTransakcja.setKlient(rezerwacja.getKlient());
            rezerwacjaTransakcja.setRezerwacja(rezerwacja);
            rezerwacjaTranzakcjaRepository.save(rezerwacjaTransakcja);

            rezerwacja.setCzyOplacona(true);
            ReservationController.getInstance(frame).rezerwacjaRepository.update(rezerwacja);

            if(previousWindow.getClass().toString().contains("ReservationSeansView")) {
                ReservationController.getInstance(frame).seansList(rezerwacja.getSeans().getSala().getKino());
            }
            if(previousWindow.getClass().toString().contains("KlientReservation")) {
                UserController userController = UserController.getInstance(frame);
                userController.details(rezerwacja.getKlient());
            }
        }
    }

    //list

    private class listExitListener implements ActionListener {
        private KlientEntity klient;

        public listExitListener(KlientEntity klient) {
            this.klient = klient;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(reservationTransactionListView);
            UserController userController = UserController.getInstance(frame);
            userController.details(klient);
        }
    }

    //details

    private class detailsExitListener implements ActionListener {
        private KlientEntity klient;

        public detailsExitListener(KlientEntity klient) {
            this.klient = klient;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(reservationTransactionView);
            list(klient);
        }
    }
}
