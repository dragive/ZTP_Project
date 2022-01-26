package org.example.jpa.controllers;

import org.example.jpa.entities.KinoEntity;
import org.example.jpa.entities.SalaEntity;
import org.example.jpa.entities.SeansEntity;
import org.example.jpa.entities.KontrahentEntity;
import org.example.jpa.repositories.KinoRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.CinemaViews.AddCinemaView;
import org.example.ui.views.CinemaViews.CinemaListView;
import org.example.ui.views.CinemaViews.CinemaView;
import org.example.ui.views.MenuPanel;
import org.example.ui.views.ReservationViews.ReservationSeansListView;
import org.example.ui.views.CinemaViews.EditCinemaView;
import org.example.ui.views.ErrorFrame;
import org.example.ui.views.PopUps;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class CinemaController {
    private static CinemaController instance;

    KinoRepository kinoRepository;

    //views
    CinemaListView cinemaListView;
    AddCinemaView addCinemaView;
    CinemaView cinemaView;
    EditCinemaView editCinemaView;

    //models
    KinoEntity kinoEntity;

    JFrame frame;

    private CinemaController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        kinoRepository = KinoRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static CinemaController getInstance(JFrame frame) {
        if(instance==null) instance = new CinemaController(frame);
        return instance;
    }

    public List<KinoEntity> getAll() {
        return kinoRepository.getAll();
    }

    public KinoEntity getByName(String name) {
        return kinoRepository.getByName(name);
    }

    public void index() {
        List<KinoEntity> kinoEntityList = getAll();
        cinemaListView = new CinemaListView(kinoEntityList);
        frame.add(cinemaListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        cinemaListView.requestFocus();

        cinemaListView.getAddCinema().addActionListener(new indexAddCinemaListener());
    }

    public void create() {
        addCinemaView = new AddCinemaView();
        frame.add(addCinemaView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        cinemaListView.requestFocus();

        addCinemaView.getAccept().addActionListener(new createAcceptListener());
        addCinemaView.getExit().addActionListener(new createExitListener());
    }

    public void details(KinoEntity kino) {
        cinemaView = new CinemaView(kino);
        frame.add(cinemaView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        cinemaView.getExit().addActionListener(new detailsExitListener());
        cinemaView.getDelete().addActionListener(new detailsDeleteListener());
        cinemaView.getRoom().addActionListener(new detailsRoomListener(kino));
        cinemaView.getReservation().addActionListener(new detailsReservationListener(kino));

        cinemaView.getEditButton().addActionListener(new detailsEditListener(kino));
    }

    public void edit(KinoEntity kino){
        editCinemaView = new EditCinemaView();
        frame.add(editCinemaView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        editCinemaView.getExit().addActionListener(new editExitListener(kino));
        editCinemaView.getAccept().addActionListener(new editAcceptListener(kino));

        editCinemaView.getDesc().setText(kino.getOpis());
        editCinemaView.getAdres().setText(kino.getAdres());
        editCinemaView.getCinemaName().setText(kino.getName());
        editCinemaView.getMiasto().setText(kino.getMiasto());


    }

    //index

    private class indexAddCinemaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(cinemaListView);
            create();
        }
    }

    //create

    private class createAcceptListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addCinemaView);
            kinoEntity = new KinoEntity();
            String city = addCinemaView.getMiasto().getText();
            try {
                kinoEntity.setId(kinoRepository.getNewId());
                kinoEntity.setName(addCinemaView.getCinemaName().getText());
                kinoEntity.setOpis(addCinemaView.getDesc().getText());
                kinoEntity.setAdres(addCinemaView.getAdres().getText());
                kinoEntity.setMiasto(city);
                kinoEntity.setPracownicy(null);
                kinoEntity.setMagazyny(null);
                kinoEntity.setSale(null);

                //CinemaEntity(kinoEntity);

                kinoRepository.save(kinoEntity);
            }
            catch (Exception ex){
                PopUps.Error("Sprawdź czy wprowadzone dane są poprawne!");
            }
            index();
        }
    }

    private class createExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addCinemaView);
            index();
        }
    }

    //details

    private class detailsExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(cinemaView);
            index();
        }
    }

    private class detailsDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(cinemaView);

            kinoEntity = cinemaView.getKinoEntity();
            try{
                if(PopUps.OkCancel("Czy na pewno chcesz usunąć to kino?"))
                    kinoRepository.delete(kinoEntity);
            }
            catch (Exception ex){
                PopUps.Error("Usuwanie się nie powiodło. Sprawdź czy kino nie posiada danych!");
            }

            index();
        }
    }

    private class detailsRoomListener implements ActionListener {
        KinoEntity kino;
        public detailsRoomListener(KinoEntity kino) {
            this.kino = kino;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(cinemaView);

            RoomController roomController = RoomController.getInstance(frame);
            roomController.index(kino);
        }
    }

    private class detailsReservationListener implements ActionListener {
        KinoEntity kino;
        public detailsReservationListener(KinoEntity kino) {
            this.kino = kino;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(cinemaView);
            ReservationController reservationController = ReservationController.getInstance(frame);
            reservationController.seansList(kino);
            /*List<SalaEntity> salaEntities = kinoRepository.getById(kino.getId()).getSale();
            List<SeansEntity> seansEntities = new ArrayList<>();
            for(SalaEntity sala: salaEntities) {
                for(SeansEntity seans: RoomController.getInstance(frame).roomRepository.getById(sala.getId()).getSeanse()) {
                    seansEntities.add(seans);
                }
            }
            ReservationSeansListView reservationSeansListView = new ReservationSeansListView(MenuPanel.user,seansEntities);
            frame.add(reservationSeansListView, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            reservationSeansListView.requestFocus();*/
        }
    }

    private class detailsEditListener implements ActionListener{
        KinoEntity kino;
        public detailsEditListener(KinoEntity kino) {
            this.kino = kino;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(cinemaView);
            edit(this.kino);
        }
    }

    //edit

    private class editExitListener implements ActionListener {
        KinoEntity kino;
        public editExitListener(KinoEntity kino){
            this.kino = kino;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(editCinemaView);
            details(kino);
        }
    }
    private  class editAcceptListener implements ActionListener{
        KinoEntity kino;
        public editAcceptListener(KinoEntity kino){this.kino=kino;}
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                KinoEntity kinoEntity = this.kino;
                String name = editCinemaView.getCinemaName().getText();
                String city = editCinemaView.getMiasto().getText();

                kinoEntity.setAdres(editCinemaView.getAdres().getText());
                kinoEntity.setOpis(editCinemaView.getDesc().getText());
                kinoEntity.setName(name);
                kinoEntity.setMiasto(city);
                kinoEntity.setAdres(editCinemaView.getAdres().getText());

                validateCinemaEntity(kinoEntity);

                kinoRepository.update(kinoEntity);

                frame.remove(editCinemaView);
                details(kino);
            }catch (Exception ex) {
                System.out.println(ex);
                PopUps.Error("Błędne dane. Sprawdź czy wszystko wpisałeś poprawnie");
            }
        }
    }

    public void validateCinemaEntity(KinoEntity kinoEntity) throws Exception{
        if(kinoEntity.getAdres().equals("") || kinoEntity.getMiasto().equals("") || kinoEntity.getName().equals("") || kinoEntity.getOpis().equals("")){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }
}
