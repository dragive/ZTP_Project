package org.example.jpa.controllers;

import org.example.dto.SalaDTO;
import org.example.dto.mappers.SalaMapper;
import org.example.jpa.entities.*;
import org.example.jpa.repositories.FotelRepository;
import org.example.jpa.repositories.KinoRepository;
import org.example.jpa.repositories.SalaRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.FilmViews.AddFilmView;
import org.example.ui.views.FilmViews.FilmListView;
import org.example.ui.views.FilmViews.FilmView;
import org.example.ui.views.MagazynViews.AddMagazynView;
import org.example.ui.views.MagazynViews.MagazynView;
import org.example.ui.views.PopUps;
import org.example.ui.views.RoomViews.AddRoomView;
import org.example.ui.views.RoomViews.RoomListView;
import org.example.ui.views.RoomViews.RoomView;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomController {
    private static RoomController instance;

    SalaRepository roomRepository;

    //views
    RoomListView roomListView;
    AddRoomView addRoomView;
    RoomView roomView;

    //models
    SalaEntity roomEntity;

    JFrame frame;

    private RoomController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        roomRepository = SalaRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static RoomController getInstance(JFrame frame) {
        if(instance==null) instance = new RoomController(frame);
        return instance;
    }

    public void index(KinoEntity kino) {
        List<SalaEntity> roomEntityAll = roomRepository.getAll();
        List<SalaEntity> roomEntitySelected = new ArrayList<>();
        for(SalaEntity room:roomEntityAll) {
            if(room.getKino().getId().equals(kino.getId())) roomEntitySelected.add(room);
        }

        roomListView = new RoomListView(roomEntitySelected);
        frame.add(roomListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        roomListView.requestFocus();

        roomListView.getAddRoom().addActionListener(new indexAddRoomListener(kino));
        roomListView.getExit().addActionListener(new indexExitListener(kino));
    }

    public void details(SalaEntity room) {

        SalaDTO salaDTO = SalaMapper.mapToDTO(room);

        roomView = new RoomView(salaDTO);
        frame.add(roomView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        roomView.requestFocus();

        roomView.getSeans().addActionListener(new detailsSeansListener(room));
        roomView.getExit().addActionListener(new detailsExitListener(room.getKino()));
        roomView.getDelete().addActionListener(new deleteRoomListener(room));
    }

    public void create(KinoEntity kino) {
        addRoomView = new AddRoomView();
        frame.add(addRoomView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addRoomView.requestFocus();

        addRoomView.getAccept().addActionListener(new createAcceptListener(kino));
        addRoomView.getExit().addActionListener(new createExitListener(kino));
    }

    //index

    private class indexAddRoomListener implements ActionListener {
        KinoEntity kinoEntity;
        private indexAddRoomListener(KinoEntity kinoEntity) {
            this.kinoEntity = kinoEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(roomListView);
            create(kinoEntity);
        }
    }


    private class indexExitListener implements ActionListener {
        KinoEntity kinoEntity;
        private indexExitListener(KinoEntity kinoEntity) {
            this.kinoEntity = kinoEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(roomListView);
            CinemaController cinemaController = CinemaController.getInstance(frame);
            cinemaController.details(kinoEntity);
        }
    }

    //delete
    private class deleteRoomListener implements ActionListener {
        SalaEntity room;
        public deleteRoomListener(SalaEntity room) {
            this.room = room;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(PopUps.OkCancel("Czy na pewno chcesz usunąć tą salę?")){
                KinoEntity kino = KinoRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById( room.getKino().getId());

                roomRepository.delete(room);

                frame.remove(roomView);

                index(kino);
            }
        }
    }

    //details

    private class detailsSeansListener implements ActionListener {
        SalaEntity room;
        public detailsSeansListener(SalaEntity room) {
            this.room = room;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(roomView);
            SeansController seansController =SeansController.getInstance(frame);
            seansController.index(room);
        }
    }


    private class detailsExitListener implements ActionListener {
        KinoEntity kinoEntity;
        private detailsExitListener(KinoEntity kinoEntity) {
            this.kinoEntity = kinoEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(roomView);
            index(kinoEntity);
        }
    }

    //create

    private class createAcceptListener implements ActionListener {
        KinoEntity kinoEntity;
        private createAcceptListener(KinoEntity kinoEntity) {
            this.kinoEntity = kinoEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            SalaEntity newRoomEntity = new SalaEntity();
            try {
                newRoomEntity.setId(roomRepository.getNewId());
                newRoomEntity.setKino(kinoEntity);
                newRoomEntity.setSeanse(null);
                newRoomEntity.setCzy3d(addRoomView.getCzy3D().isSelected());
                newRoomEntity.setNazwa(addRoomView.getRoomName().getText());
                newRoomEntity.setLiczbaMiejsc(Long.parseLong(addRoomView.getCols().getText())*Long.parseLong(addRoomView.getRows().getText()));
                newRoomEntity.setMiejscWRzedzie(Long.parseLong(addRoomView.getCols().getText()));
                newRoomEntity.setLiczbaRzedow(Long.parseLong(addRoomView.getRows().getText()));
                newRoomEntity.setCzyAudio(addRoomView.getCzyLepszyDzwiek().isSelected());
                newRoomEntity.setCzyLepszeSiedzenia(addRoomView.getCzyLepszeMiejsca().isSelected());
                newRoomEntity.setCzyNiepelnosprawni(addRoomView.getCzyDlaNiepelnosprawnych().isSelected());
                newRoomEntity.setFotele(null);

                validateRoomEntity(newRoomEntity);

                roomRepository.save(newRoomEntity);
            }
            catch (Exception ex){
                PopUps.Error("Wystąpił błąd. Sprawdź czy wprowadzone dane są poprawne!");
            }


            //newRoomEntity.setFotele(SeatsController.getInstance(frame).create(newRoomEntity));

            SeatsController.getInstance(frame).create(newRoomEntity);

            frame.remove(addRoomView);
            index(kinoEntity);
        }
    }

    private class createExitListener implements ActionListener {
        KinoEntity kinoEntity;
        private createExitListener(KinoEntity kinoEntity) {
            this.kinoEntity = kinoEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addRoomView);
            index(kinoEntity);
        }
    }

    public void validateRoomEntity(SalaEntity salaEntity) throws Exception{
        if(salaEntity.getNazwa().equals("") || salaEntity.getMiejscWRzedzie().equals("") || salaEntity.getMiejscWRzedzie()<1 || salaEntity.getLiczbaRzedow().equals("") || salaEntity.getLiczbaRzedow()<1){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }

}
