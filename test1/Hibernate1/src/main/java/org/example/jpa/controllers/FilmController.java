package org.example.jpa.controllers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.repositories.FilmRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.CinemaViews.AddCinemaView;
import org.example.ui.views.CinemaViews.CinemaListView;
import org.example.ui.views.CinemaViews.CinemaView;
import org.example.ui.views.ErrorFrame;
import org.example.ui.views.FilmViews.AddFilmView;
import org.example.ui.views.FilmViews.EditFilmView;
import org.example.ui.views.FilmViews.FilmListView;
import org.example.ui.views.FilmViews.FilmView;
import org.example.ui.views.PopUps;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
public class FilmController {
    private static FilmController instance;

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    FilmRepository filmRepository;

    //views
    FilmListView filmListView;
    AddFilmView addFilmView;
    FilmView filmView;
    EditFilmView editFilmView;
    

    //models
    FilmEntity filmEntity;

    JFrame frame;

    private FilmController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        filmRepository = FilmRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static FilmController getInstance(JFrame frame) {
        if(instance==null) instance = new FilmController(frame);
        return instance;
    }

    public void index() {
        List<FilmEntity> filmEntityList = filmRepository.getAll();
        filmListView = new FilmListView(filmEntityList);
        frame.add(filmListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        filmListView.requestFocus();

        filmListView.getAddFilm().addActionListener(new indexAddFilmListener());
    }

    public void details(FilmEntity film) {
        filmView = new FilmView(film);
        frame.add(filmView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        filmView.requestFocus();

        filmView.getExit().addActionListener(new detailsExitListener());
        filmView.getEdit().addActionListener(new detailsEditListener(film));
        filmView.getDelete().addActionListener(new detailsDeleteListener(film));
    }

    public void create() {
        addFilmView = new AddFilmView();
        frame.add(addFilmView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addFilmView.requestFocus();

        addFilmView.getAccept().addActionListener(new createAcceptListener());
        addFilmView.getExit().addActionListener(new createExitListener());
    }

    public void edit(FilmEntity filmEntity) {
        editFilmView = new EditFilmView();
        frame.add(editFilmView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        editFilmView.requestFocus();

        editFilmView.getAccept().addActionListener(new editAcceptListener(filmEntity));
        editFilmView.getExit().addActionListener(new editExitListener());


        editFilmView.getTitle().setText(filmEntity.getTitle());
        editFilmView.getOpis().setText(filmEntity.getOpis());
        editFilmView.getDataWydania().setText(filmEntity.getDataWydania().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        editFilmView.getWiek().setText(filmEntity.getWiek().toString());
        editFilmView.getCzasTrwania().setText(filmEntity.getCzasTrwania().toString());
        editFilmView.getCzy3D().setSelected(filmEntity.getCzy3d());
        editFilmView.getCzyNapisy().setSelected(filmEntity.getCzyNapisy());
        editFilmView.getCzyDubbing().setSelected(filmEntity.getCzyDubbing());
        editFilmView.getCzyOriginal().setSelected(filmEntity.getCzyOriginal());



    }

    //index

    private class indexAddFilmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(filmListView);
            create();
        }
    }

    //details

    private class detailsExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(filmView);
            index();
        }
    }

    private class detailsEditListener implements ActionListener {
        FilmEntity film;
        detailsEditListener(FilmEntity film){
            this.film = film;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(filmView);
            edit(film);
        }
    }

    //delete

    private class detailsDeleteListener implements ActionListener {
        FilmEntity film;
        detailsDeleteListener(FilmEntity film){
            this.film = film;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(PopUps.OkCancel("Czy na pewno chcesz usunąć ten film?")){
                filmRepository.delete(film);

                frame.remove(filmView);
                index();
            }

        }
    }

    //create

    private class createExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addFilmView);
            index();
        }
    }

    private class createAcceptListener implements ActionListener {
        @SneakyThrows
        @Override
        public void actionPerformed(ActionEvent e) {
            FilmEntity newFilmEntity = new FilmEntity();
            try {
                newFilmEntity.setTitle(addFilmView.getTitle().getText());
                newFilmEntity.setOpis(addFilmView.getOpis().getText());
                newFilmEntity.setCzasTrwania(Double.parseDouble(addFilmView.getCzasTrwania().getText()));
                newFilmEntity.setCzy3d(addFilmView.getCzy3D().isSelected());
                newFilmEntity.setCzyNapisy(addFilmView.getCzyNapisy().isSelected());
                newFilmEntity.setCzyDubbing(addFilmView.getCzyDubbing().isSelected());
                newFilmEntity.setCzyOriginal(addFilmView.getCzyOriginal().isSelected());
                newFilmEntity.setWiek(Long.parseLong(addFilmView.getWiek().getText()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
                LocalDate date = LocalDate.parse(addFilmView.getDataWydania().getText(), formatter);
                newFilmEntity.setDataWydania(date);
                newFilmEntity.setId(filmRepository.getNewId());
                newFilmEntity.setSeansEntities(null);

                validateFilmEntity(newFilmEntity);

                filmRepository.save(newFilmEntity);

                frame.remove(addFilmView);
                index();
            } catch (Exception ex) {
                PopUps.Error("Błędne dane. Sprawdź czy wszystko wpisałeś dobrze");
            }
        }
    }

            //edit

    private class editExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addFilmView);
            index();
        }
    }

    private class editAcceptListener implements ActionListener {
        FilmEntity film;
        editAcceptListener(FilmEntity film){
            this.film = film;
        }
        @SneakyThrows
        @Override
        public void actionPerformed(ActionEvent e) {
            FilmEntity newFilmEntity = film;
            try {
                newFilmEntity.setTitle(editFilmView.getTitle().getText());
                newFilmEntity.setOpis(editFilmView.getOpis().getText());
                newFilmEntity.setCzasTrwania(Double.parseDouble(editFilmView.getCzasTrwania().getText()));
                newFilmEntity.setCzy3d(editFilmView.getCzy3D().isSelected());
                newFilmEntity.setCzyNapisy(editFilmView.getCzyNapisy().isSelected());
                newFilmEntity.setCzyDubbing(editFilmView.getCzyDubbing().isSelected());
                newFilmEntity.setCzyOriginal(editFilmView.getCzyOriginal().isSelected());
                newFilmEntity.setWiek(Long.parseLong(editFilmView.getWiek().getText()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
                LocalDate date = LocalDate.parse(editFilmView.getDataWydania().getText(), formatter);
                newFilmEntity.setDataWydania(date);
//                newFilmEntity.setId(filmRepository.getNewId());
                newFilmEntity.setSeansEntities(null);
                filmRepository.update(newFilmEntity);

                frame.remove(editFilmView);
                details(newFilmEntity);
            } catch (Exception ex) {
                log.error(ex.toString());
                new ErrorFrame("Błędne dane. Sprawdź czy wszystko wpisałeś dobrze");
            }
        }
    }

    public void validateFilmEntity(FilmEntity newFilmEntity) throws Exception{
        if(newFilmEntity.getTitle().equals("") || newFilmEntity.getOpis().equals("") || newFilmEntity.getCzasTrwania().equals("") || newFilmEntity.getCzasTrwania()>0 || newFilmEntity.getWiek().equals("") || newFilmEntity.getWiek()>1 || newFilmEntity.getDataWydania().equals("")){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }
}
