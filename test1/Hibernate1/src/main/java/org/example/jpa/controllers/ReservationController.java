package org.example.jpa.controllers;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.K;
import org.example.jpa.entities.*;
import org.example.jpa.repositories.*;
import org.example.services.DatabaseService;
import org.example.ui.views.MenuPanel;
import org.example.ui.views.ReservationViews.ReservationCinemaListView;
import org.example.ui.views.ReservationViews.ReservationSeansListView;
import org.example.ui.views.ReservationViews.ReservationSeansView;
import org.example.ui.views.ReservationViews.ReservationView;
import org.example.ui.views.SeansViews.AddSeansView;
import org.example.ui.views.SeansViews.SeansAddFilmView;
import org.example.ui.views.SeansViews.SeansListView;
import org.example.ui.views.SeansViews.SeansView;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReservationController {
    private static ReservationController instance;

    public RezerwacjaRepository rezerwacjaRepository;

    //views
    ReservationCinemaListView reservationCinemaListView;
    ReservationView reservationView;
    ReservationSeansListView reservationSeansListView;
    ReservationSeansView reservationSeansView;

    //models
    RezerwacjaEntity rezerwacjaEntity;

    JFrame frame;

    private ReservationController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        rezerwacjaRepository = RezerwacjaRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static ReservationController getInstance(JFrame frame) {
        if(instance==null) instance = new ReservationController(frame);
        return instance;
    }

    public void index() {
        List<KinoEntity> kinoEntityList = CinemaController.getInstance(frame).getAll();
        reservationCinemaListView = new ReservationCinemaListView(kinoEntityList);
        frame.add(reservationCinemaListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        reservationCinemaListView.requestFocus();
    }


    public void seansList(KinoEntity kinoEntity) {
        List<SalaEntity> salaEntities = CinemaController.getInstance(frame).kinoRepository.getById(kinoEntity.getId()).getSale();
        List<SeansEntity> seansEntities = new ArrayList<>();
        for(SalaEntity sala: salaEntities) {
            for(SeansEntity seans: RoomController.getInstance(frame).roomRepository.getById(sala.getId()).getSeanse()) {
                seansEntities.add(seans);
            }
        }
        reservationSeansListView = new ReservationSeansListView(seansEntities);
        frame.add(reservationSeansListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        reservationSeansListView.requestFocus();

        reservationSeansListView.getExit().addActionListener(new seansListExitListener(kinoEntity));
    }

    //tu sie zaczyna
    public void seatsList(SeansEntity seans, int cols) {
        //lists
        List<JButton> seats = new ArrayList<>();
        List<FotelEntity> fotelEntityList = RoomController.getInstance(frame).roomRepository.getById(seans.getSala().getId()).getFotele();
        List<FotelEntity> fotelEntityListForReservation = new ArrayList<>();

        //colors
        Color red = new Color(198, 14, 29);
        Color green = new Color(50,205,50);
        Color yellow = new Color(249,215,28);

        RezerwacjaFotelRepository rezerwacjaFotelRepository = RezerwacjaFotelRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build();
        //TUTAJ TWORZYMY REZERWACJE - STAN IN PROGRESS
        RezerwacjaEntity rezerwacja = new RezerwacjaEntity();

        for(FotelEntity fotelEntity:fotelEntityList) {
            JButton tempBtn = new JButton();
            boolean flag = false;
//            List<RezerwacjaEntity> rezerwacjaEntityList = SeatsController.getInstance(frame).fotelRepository.getById(fotelEntity.getId()).getFotelReservations();
            List<RezerwacjaEntity> rezerwacjaEntityList = FotelRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById(fotelEntity.getId()).getFotelReservations();
            for(RezerwacjaEntity rezerwacjaEntity: rezerwacjaEntityList) {
                if(rezerwacjaEntity.getSeans().getId().equals(seans.getId())) {
                    tempBtn.setBackground(red);
                    tempBtn.setOpaque(true);
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                tempBtn.setBackground(green);
                tempBtn.setOpaque(true);
            }
            tempBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(tempBtn.getBackground().equals(green)) {
                        tempBtn.setBackground(yellow);

                        fotelEntityListForReservation.add(fotelEntity);
                    }
                    else if(tempBtn.getBackground().equals(yellow)) {
                        tempBtn.setBackground(green);

                        fotelEntityListForReservation.remove(fotelEntity);
                    }
                }
            });
            seats.add(tempBtn);
        }

        reservationSeansView = new ReservationSeansView(seats,cols);
        frame.add(reservationSeansView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        reservationSeansView.requestFocus();

        reservationSeansView.getAccept().addActionListener(new seatListAcceptListener(seans,fotelEntityListForReservation,rezerwacja));
        reservationSeansView.getExit().addActionListener(new seatListExitListener(seans,rezerwacja));
    }

    private class seansListExitListener implements ActionListener {
        KinoEntity kinoEntity;
        public seansListExitListener(KinoEntity kinoEntity) {
            this.kinoEntity = kinoEntity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(reservationSeansListView);
            CinemaController.getInstance(frame).details(kinoEntity);
        }
    }

    private class seatListAcceptListener implements ActionListener {
        SeansEntity seans;
        List<FotelEntity> fotelEntityList;
        RezerwacjaEntity rezerwacja;
        public seatListAcceptListener(SeansEntity seans, List<FotelEntity> fotelEntityList, RezerwacjaEntity rezerwacja) {
            this.seans=seans;
            this.fotelEntityList=fotelEntityList;
            this.rezerwacja=rezerwacja;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //REZERWACJA ZAAKCEPTOWANA
            rezerwacja.getReservationState().accept();

            //start
            rezerwacja.setKlient(null);
            rezerwacja.setId(rezerwacjaRepository.getNewId());
            rezerwacja.setDataRezerwacji(LocalDate.now());

            /*if(MenuPanel.user instanceof PracownikEntity) {
                //OPLACONA
                rezerwacja.getReservationState().pay();
            }
            else {
                rezerwacja.setCzyOplacona(false);
            }*/
            rezerwacja.setCzyOplacona(false);
            rezerwacja.getReservationFotels();
            rezerwacja.setSeans(seans);

            StringBuilder stringBuilder = new StringBuilder();
            for(FilmEntity filmEntity:seans.getSeansFilms()) {
                stringBuilder.append(filmEntity.getTitle()).append(" ");
            }
            stringBuilder.append(seans.getGodzinaRozpoczecia().toString()).append(" - ").append(seans.getGodzinaZakonczenia());

            rezerwacja.setNazwa(stringBuilder.toString());
            RezerwacjaRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().save(rezerwacja);

//            SeatsController.getInstance(frame).update(fotelEntityList,rezerwacja);
//            FotelRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build();

            RezerwacjaFotelRepository rezerwacjaFotelRepository = RezerwacjaFotelRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build();

            Long reservationId = rezerwacja.getId();
            for(FotelEntity fotelEntity: fotelEntityList){
                RezerwacjaFotelEntity rezerwacjaFotelEntity = new RezerwacjaFotelEntity();

                rezerwacjaFotelEntity.getId().setRezerwacjaId(reservationId);
                rezerwacjaFotelEntity.getId().setFotelId(fotelEntity.getId());
                rezerwacjaFotelRepository.save(rezerwacjaFotelEntity);
            }

            //frame.remove(reservationSeansView);

            if(MenuPanel.user instanceof KlientEntity) {
                frame.remove(reservationSeansView);
                rezerwacja.setKlient((KlientEntity) MenuPanel.user);
                seansList(seans.getSala().getKino());
                RezerwacjaRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().update(rezerwacja);

            }
            else {
                UserController.getInstance(frame).klientRepository.getAll();
                JDialog jDialog =new JDialog(frame,"Wybierz klienta z listy");

                JPanel panelContainer = new JPanel(new BorderLayout());

                JPanel panel = new JPanel(new GridLayout(0,1));

                JPanel borderLayout = new JPanel(new BorderLayout());
                borderLayout.add(panel,BorderLayout.NORTH);


                JScrollPane jScrollPane = new JScrollPane(borderLayout);
                jScrollPane.getVerticalScrollBar().setUnitIncrement(16);


                panelContainer.add(jScrollPane ,BorderLayout.CENTER);

                List<KlientEntity> klientEntities = KlientRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getAll().stream().sorted(Comparator.comparing(KlientEntity::getNazwisko)).collect(Collectors.toList());

                for (KlientEntity klientEntity : klientEntities) {

                    JButton temp = new JButton(klientEntity.getNazwisko());
                    panel.add(temp);

                    temp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            List<RezerwacjaEntity> rezerwacjaEntityList = klientEntity.getRezerwacje();
                            rezerwacjaEntityList.add(rezerwacja);
                            klientEntity.setRezerwacje(rezerwacjaEntityList);
                            UserController.getInstance(frame).klientRepository.update(klientEntity);
                            rezerwacja.setKlient(klientEntity);
                            rezerwacja.setCzyOplacona(true);
                            rezerwacjaRepository.update(rezerwacja);

                            jDialog.setVisible(false);
                            frame.remove(reservationSeansView);
                            ReservationTransactionController.getInstance(frame).create(rezerwacja,reservationSeansView);
                        }
                    });
                }

                JButton cancel = new JButton("Anuluj");
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
    }

    private class seatListExitListener implements ActionListener {
        SeansEntity seans;
        RezerwacjaEntity rezerwacja;

        public seatListExitListener(SeansEntity seans,RezerwacjaEntity rezerwacja) {
            this.seans=seans;
            this.rezerwacja = rezerwacja;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            rezerwacja.getReservationState().abort();
            warnPopUp(seans,rezerwacja);
        }
    }


    public void warnPopUp(SeansEntity seans,RezerwacjaEntity rezerwacja) {
        JDialog jDialog =new JDialog(frame,"Zakończ");
        JPanel panelContainer = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));
        panelContainer.add(panel,BorderLayout.CENTER);

        JLabel jLabel = new JLabel("Czy na pewno chcesz anulować rezerwację?");

        JButton submit = new JButton("Tak");
        JButton cancel = new JButton("Wstecz");

        panel.add(jLabel);
        panel.add(new JLabel(""));

        JPanel buttons = new JPanel();
        buttons.add(submit);
        buttons.add(cancel);
        panel.add(buttons);

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        jDialog.add(panelContainer);

        jDialog.setVisible(true);
        jDialog.requestFocus();
        jDialog.setSize(340,200);
        jDialog.setLocationRelativeTo(null);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(false);
                rezerwacja.getReservationState().progress();
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rezerwacja.getReservationState().abort();
                jDialog.setVisible(false);
                frame.remove(reservationSeansView);
                seansList(seans.getSala().getKino());
            }
        });
    }
}
