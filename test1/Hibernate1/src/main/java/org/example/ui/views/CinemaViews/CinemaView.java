package org.example.ui.views.CinemaViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.MenuPanel;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class CinemaView extends BaseView {
    JButton exit = new JButton("Wstecz");
    JButton room = new JButton("Sale kinowe");
    JButton delete = new JButton("Usuń kino");
    JButton editButton = new JButton("Edytuj kino");
    JButton reservation = new JButton("Zarezerwuj");

    JLabel cinemaName;
    JLabel cinemaNameText = new JLabel("Nazwa kina");

    JLabel desc;
    JLabel descText = new JLabel("Nazwa kina");

    JLabel address;
    JLabel addressText = new JLabel("Adres kina");

    JLabel city;
    JLabel cityText = new JLabel("Miasto kina");

    JLabel message = new JLabel("Wybierz salę lub rezerwazję w tym kinie:");

    KinoEntity kinoEntity;

    public CinemaView(KinoEntity kinoEntity) {
        super(new GridLayout(0,2));
        this.kinoEntity = kinoEntity;
        cinemaName = new JLabel(kinoEntity.getName());
        desc = new JLabel(kinoEntity.getOpis());
        address = new JLabel(kinoEntity.getAdres());
        city = new JLabel(kinoEntity.getMiasto());

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(message,BaseViewConstraint.HEADER);

        this.add(cinemaNameText,BaseViewConstraint.CENTER);
        this.add(cinemaName,BaseViewConstraint.CENTER);

        this.add(descText,BaseViewConstraint.CENTER);
        this.add(desc,BaseViewConstraint.CENTER);

        this.add(addressText,BaseViewConstraint.CENTER);
        this.add(address,BaseViewConstraint.CENTER);

        this.add(cityText,BaseViewConstraint.CENTER);
        this.add(city,BaseViewConstraint.CENTER);

        this.add(new JLabel(""),BaseViewConstraint.CENTER);
        this.add(room,BaseViewConstraint.CENTER);

        this.add(new JLabel(""),BaseViewConstraint.CENTER);
        this.add(reservation,BaseViewConstraint.CENTER);

        if (MenuPanel.user instanceof PracownikEntity)
        {
            setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);
            this.add(editButton,BaseViewConstraint.FOOTER);
            this.add(delete,BaseViewConstraint.FOOTER);
        }
        else {
            this.add(exit,BaseViewConstraint.FOOTER);
        }
    }
}
