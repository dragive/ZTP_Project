package org.example.jpa.controllers;

import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.entities.SalaEntity;
import org.example.jpa.entities.SeansEntity;
import org.example.jpa.repositories.SalaRepository;
import org.example.jpa.repositories.SeansRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.PopUps;
import org.example.ui.views.RoomViews.AddRoomView;
import org.example.ui.views.RoomViews.RoomListView;
import org.example.ui.views.RoomViews.RoomView;
import org.example.ui.views.SeansViews.AddSeansView;
import org.example.ui.views.SeansViews.SeansAddFilmView;
import org.example.ui.views.SeansViews.SeansListView;
import org.example.ui.views.SeansViews.SeansView;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SeansController {
    private static SeansController instance;

    SeansRepository seansRepository;

    //views
    SeansListView seansListView;
    SeansView seansView;
    AddSeansView addSeansView;
    SeansAddFilmView seansAddFilmView;

    //models
    SeansEntity seansEntity;

    JFrame frame;

    private SeansController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        seansRepository = SeansRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static SeansController getInstance(JFrame frame) {
        if(instance==null) instance = new SeansController(frame);
        return instance;
    }

    public void index(SalaEntity room) {
        //List<SeansEntity> seansEntityList = RoomController.getInstance(frame).roomRepository.getById(room.getId()).getSeanse();
        //SeansRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getAll()
        List<SeansEntity> seansEntityList = SalaRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById(room.getId()).getSeanse();
        seansListView = new SeansListView(seansEntityList);
        frame.add(seansListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        seansListView.requestFocus();

        seansListView.getAddSeans().addActionListener(new indexAddRoomListener(room));
        seansListView.getExit().addActionListener(new indexExitListener(room));
    }

    public void details(SeansEntity seans) {
        seansView = new SeansView(seans);
        frame.add(seansView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        seansView.requestFocus();

        seansView.getExit().addActionListener(new detailsExitListener(seans.getSala()));
    }

    public void create(SalaEntity room) {
        addSeansView = new AddSeansView(room);
        frame.add(addSeansView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addSeansView.requestFocus();

        addSeansView.getFilm().addActionListener(new createFilmListener(room));
        addSeansView.getExit().addActionListener(new createExitListener(room));
    }

    public void create(SeansEntity seans,FilmEntity filmEntity) {
        List<FilmEntity> filmEntityList = seans.getSeansFilms();
        filmEntityList.add(filmEntity);
        if(seans.getGodzinaZakonczenia()==null) seans.setGodzinaZakonczenia(seans.getGodzinaRozpoczecia().plusMinutes(filmEntity.getCzasTrwania().longValue()));
        else seans.setGodzinaZakonczenia(seans.getGodzinaZakonczenia().plusMinutes(filmEntity.getCzasTrwania().longValue()));
        seans.setSeansFilms(filmEntityList);
        addSeansView = new AddSeansView(seans);
        frame.add(addSeansView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addSeansView.requestFocus();

        addSeansView.getAccept().addActionListener(new createAcceptListener(seans));
        addSeansView.getFilm().addActionListener(new createFilmNextListener(seans));
        addSeansView.getExit().addActionListener(new createExitListener(seans.getSala()));
    }

    public void addFilm(SeansEntity seans) {

        System.out.println(seans.getGodzinaRozpoczecia().toString());
        seansAddFilmView = new SeansAddFilmView(seans,FilmController.getInstance(frame).filmRepository.getAll());
        frame.add(seansAddFilmView);
        frame.revalidate();
        frame.repaint();
        seansAddFilmView.requestFocus();
    }



    private class indexExitListener implements ActionListener {
        SalaEntity room;
        public indexExitListener(SalaEntity room) {
            this.room = room;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(seansListView);
            RoomController roomController = RoomController.getInstance(frame);
            roomController.details(room);
        }
    }

    private class indexAddRoomListener implements ActionListener {
        SalaEntity room;
        public indexAddRoomListener(SalaEntity room) {
            this.room = room;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(seansListView);
            addSeansView = new AddSeansView(room);
            create(room);
        }
    }
    //index
//
//    private class indexAddRoomListener implements ActionListener {
//        KinoEntity kinoEntity;
//        private indexAddRoomListener(KinoEntity kinoEntity) {
//            this.kinoEntity = kinoEntity;
//        }
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            frame.remove(roomListView);
//            create(kinoEntity);
//        }
//    }
//
//
//    private class indexExitListener implements ActionListener {
//        KinoEntity kinoEntity;
//        private indexExitListener(KinoEntity kinoEntity) {
//            this.kinoEntity = kinoEntity;
//        }
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            frame.remove(roomListView);
//            CinemaController cinemaController = CinemaController.getInstance(frame);
//            cinemaController.details(kinoEntity);
//        }
//    }

    //details

    private class detailsExitListener implements ActionListener {
        SalaEntity room;
        public detailsExitListener(SalaEntity room) {
            this.room = room;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(seansView);
            index(room);
        }
    }

    //create

    private class createExitListener implements ActionListener {
        SalaEntity room;
        public createExitListener(SalaEntity room) {
            this.room = room;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addSeansView);
            index(room);
        }
    }

    private class createFilmListener implements ActionListener {
        SalaEntity room;
        private createFilmListener(SalaEntity room) {
            this.room = room;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            SeansEntity seans = new SeansEntity();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                seans.setGodzinaRozpoczecia(LocalDateTime.parse(addSeansView.getStartDate().getText(),formatter));
                seans.setCena(Double.parseDouble(addSeansView.getPrice().getText()));
                seans.setSala(room);

                validateSeansController(seans);
            }
            catch (Exception ex){
                PopUps.Error("Wystąpił błąd podczas dodawania filmu! Sprawdź czy wprowadzone dane w formularzu są poprawne!");
            }
            frame.remove(addSeansView);
            frame.revalidate();
            frame.repaint();
            addFilm(seans);
        }
    }

    private class createFilmNextListener implements ActionListener {
        SeansEntity seans;
        public createFilmNextListener(SeansEntity seans) {
            this.seans = seans;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addSeansView);
            frame.revalidate();
            frame.repaint();
            addFilm(seans);
        }
    }

    private class createAcceptListener implements ActionListener {
        SeansEntity seans;
        public createAcceptListener(SeansEntity seans) {
            this.seans = seans;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addSeansView);
            try {
                if(seans.getId()==null) seans.setId(seansRepository.getNewId());
                seansRepository.save(seans);
                SalaEntity room = seans.getSala();
                List<SeansEntity> seansEntities = room.getSeanse();
                seansEntities.add(seans);

                validateSeansController(seans);

                room.setSeanse(seansEntities);
                RoomController.getInstance(frame).roomRepository.update(room);
                index(room);
            }
            catch (Exception ex){
                PopUps.Error("Wystąpił błąd podczas tworzenia seansu! Sprawdź czy formularz został poprawnie wypełniony!");
            }
        }
    }
//
//    private class detailsExitListener implements ActionListener {
//        KinoEntity kinoEntity;
//        private detailsExitListener(KinoEntity kinoEntity) {
//            this.kinoEntity = kinoEntity;
//        }
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            frame.remove(roomView);
//            index(kinoEntity);
//        }
//    }

    //create
//
//    private class createAcceptListener implements ActionListener {
//        KinoEntity kinoEntity;
//        private createAcceptListener(KinoEntity kinoEntity) {
//            this.kinoEntity = kinoEntity;
//        }
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            SalaEntity newRoomEntity = new SalaEntity();
//            newRoomEntity.setId(roomRepository.getNewId());
//            newRoomEntity.setKino(kinoEntity);
//            newRoomEntity.setFotele(null);
//            newRoomEntity.setSeanse(null);
//            newRoomEntity.setCzy3d(addRoomView.getCzy3D().isSelected());
//            newRoomEntity.setNazwa(addRoomView.getRoomName().getText());
//            newRoomEntity.setLiczbaMiejsc(Long.parseLong(addRoomView.getCols().getText())*Long.parseLong(addRoomView.getRows().getText()));
//            newRoomEntity.setLiczbaMiejscWRzedzie(Long.parseLong(addRoomView.getCols().getText()));
//            newRoomEntity.setLiczbaRzedow(Long.parseLong(addRoomView.getRows().getText()));
//            roomRepository.save(newRoomEntity);
//
//            frame.remove(addRoomView);
//            index(kinoEntity);
//        }
//    }
//
//    private class createExitListener implements ActionListener {
//        KinoEntity kinoEntity;
//        private createExitListener(KinoEntity kinoEntity) {
//            this.kinoEntity = kinoEntity;
//        }
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            frame.remove(roomView);
//            index(kinoEntity);
//        }
//    }

    public void validateSeansController(SeansEntity seansEntity) throws Exception{
        if(seansEntity.getCena().equals("") || seansEntity.getCena()<0 || seansEntity.getGodzinaRozpoczecia().equals("")){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }
}
