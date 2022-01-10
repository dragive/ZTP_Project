package org.example.ui.views.SeansViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.SalaEntity;
import org.example.jpa.entities.SeansEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class AddSeansView extends BaseView {
    JLabel startDateText = new JLabel("Data rozpoczęcia (DD-MM-RRRR HH:MM:SS)");
    JTextField startDate;

    JLabel endDateText = new JLabel("Data zakończenia (DD-MM-RRRR HH:MM:SS)");
    JLabel endDate;

    JLabel priceText = new JLabel("Cena za bilet");
    JTextField price;

    SalaEntity room;

    JButton accept = new JButton("Zaakceptuj");
    JButton film = new JButton("Dodaj film");
    JButton exit = new JButton("Wstecz");

    public AddSeansView(SalaEntity room) {
        super(new GridLayout(0,2));
        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);
        this.room = room;
        startDate = new JTextField();
        price = new JTextField();
        this.add(startDateText,BaseViewConstraint.CENTER);
        this.add(startDate,BaseViewConstraint.CENTER);

        this.add(priceText,BaseViewConstraint.CENTER);
        this.add(price,BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(film,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }

    public AddSeansView(SeansEntity seans) {
        super(new GridLayout(0,2));
        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);
        startDate = new JTextField();
        endDate = new JLabel(seans.getGodzinaZakonczenia().toString());
        startDate.setText(seans.getGodzinaRozpoczecia().toString());
        endDate.setText(seans.getGodzinaZakonczenia().toString());
        price = new JTextField();
        price.setText(seans.getCena().toString());

        this.add(startDateText, BaseViewConstraint.CENTER);
        this.add(startDate, BaseViewConstraint.CENTER);

        this.add(endDateText, BaseViewConstraint.CENTER);
        this.add(endDate, BaseViewConstraint.CENTER);

        this.add(priceText, BaseViewConstraint.CENTER);
        this.add(price, BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);
        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(film,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
