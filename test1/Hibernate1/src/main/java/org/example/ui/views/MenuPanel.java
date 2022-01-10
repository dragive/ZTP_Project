package org.example.ui.views;

import org.example.jpa.controllers.*;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends BaseView {
    public static JPanel bottomPanel;
    public static User user;
    public static MenuPanel instance;

    UserController userController;
    FilmController filmController;
    KontrahentController kontrahentController;
    MagazynController magazynController;
    HomeController homeController;
    ItemController itemController;
    CinemaController cinemaController;
    ReservationController reservationController;

    JButton cinemas = new JButton("Kina");
    JButton films = new JButton("Filmy");
    JButton warehouses = new JButton("Magazyny");
    JButton kontrahenci = new JButton("Kontrahenci");
    JButton przedmioty = new JButton("Przedmioty");
    JButton users = new JButton("Użytkownicy");
    JButton userButton = new JButton("Użytkownik");
    JButton credits = new JButton("Twórcy");
    JButton reservation = new JButton("Zarezerwuj");
    JButton logout = new JButton("Wyloguj");

    public MenuPanel(JFrame frame, User _user) {
        super(new GridLayout(1,0));
        user = _user;
        instance = this;
        this.frame = frame;
        this.add(cinemas);
        this.add(films);
        this.add(reservation);
        if(user instanceof PracownikEntity) {
            this.add(warehouses);
            this.add(przedmioty);
            this.add(kontrahenci);
            this.add(users);
        }
        this.add(userButton);
        this.add(credits);
        this.add(logout);

        setActionListeners();
    }

    private void setActionListeners(){
        addLogoutListener();
        addCreditsListener();
        addCinemasListener();
        addUsersListener();
        addUserButtonListener();
        addFilmsListener();
        addWarehouseListener();
        addItemsListener();
        addKontrahenciListener();
        addReservationListener();
    }

    public void addKontrahenciListener() {
        kontrahenci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("KontrahentListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(bottomPanel);
                    kontrahentController = KontrahentController.getInstance(frame);
                    kontrahentController.index();
                }
            }
        });
    }

    public void addWarehouseListener() {
        warehouses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("MagazynListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(bottomPanel);
                    magazynController = MagazynController.getInstance(frame);
                    magazynController.index();
                }
            }
        });
    }

    public void addCinemasListener() {
        cinemas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("CinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(bottomPanel);
                    cinemaController = CinemaController.getInstance(frame);
                    cinemaController.index();
                }
            }
        });
    }

    public void addFilmsListener() {
        films.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("FilmListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(bottomPanel);
                    filmController = FilmController.getInstance(frame);
                    filmController.index();
                }
            }
        });
    }

    public void addReservationListener() {

        reservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("ReservationCinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(bottomPanel);
                    reservationController = ReservationController.getInstance(frame);
                    reservationController.index();
                }
            }
        });
    }

    public void addUsersListener() {
        users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                frame.remove(bottomPanel);
                if(userController == null) userController = UserController.getInstance(frame);
                userController.list();

            }
        });
    }

    public void addUserButtonListener() {
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                frame.remove(bottomPanel);
                if(userController == null) userController = UserController.getInstance(frame);
                userController.details(user);

            }
        });
    }

    public void addCreditsListener() {
        credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("CreditsView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(bottomPanel);
                    homeController = HomeController.getInstance(frame);
                    homeController.credits();
                }
            }
        });
    }

    public void addLogoutListener() {
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                frame.remove(mainContainerPanel);
                frame.remove(bottomPanel);
                user=null;

                if(userController==null) userController = UserController.getInstance(frame);
                userController.login();
            }
        });
    }

    public void addItemsListener(){
        przedmioty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                frame.remove(bottomPanel);
                itemController = ItemController.getInstance(frame);
                itemController.index();
            }
        });
    }

}
