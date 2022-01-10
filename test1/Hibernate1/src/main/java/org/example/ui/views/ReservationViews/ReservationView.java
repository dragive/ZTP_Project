package org.example.ui.views.ReservationViews;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;
import org.example.jpa.controllers.SeansController;
import org.example.jpa.entities.*;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.CodingErrorAction;
import java.util.List;

@Getter
@Setter
public class ReservationView extends BaseView {
    JLabel seansText = new JLabel("Tytuł");
    JLabel seansTitle;

    JLabel dateText = new JLabel("Data seansu");
    JLabel date;

    JLabel reservationDateText = new JLabel("Data rezerwacji");
    JLabel reservationDate;

    JCheckBox checkBox = new JCheckBox();
    JLabel paidText = new JLabel("Opłacona");


    JButton exit = new JButton("Wstecz");

    public ReservationView(User panelUser, RezerwacjaEntity reservation, SeansEntity seans) {
        super(new GridLayout(0,2));
        List<FilmEntity> tempList = seans.getSeansFilms();
        StringBuilder stringBuilder = new StringBuilder();
        for(FilmEntity filmEntity:tempList) {
            stringBuilder.append(filmEntity.getTitle()+" ");
        }
        seansTitle = new JLabel(stringBuilder.toString());
        date = new JLabel(seans.getGodzinaRozpoczecia().toString() + " - " + seans.getGodzinaZakonczenia());
        reservationDate = new JLabel(reservation.getDataRezerwacji().toString());

        if(MenuPanel.user instanceof KlientEntity) {
            checkBox.setEnabled(false);
        }

        checkBox.setSelected(reservation.getCzyOplacona());

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(seansText, BaseViewConstraint.CENTER);
        this.add(seansTitle, BaseViewConstraint.CENTER);

        this.add(dateText, BaseViewConstraint.CENTER);
        this.add(date, BaseViewConstraint.CENTER);

        this.add(reservationDateText, BaseViewConstraint.CENTER);
        this.add(reservationDate, BaseViewConstraint.CENTER);

        this.add(paidText, BaseViewConstraint.CENTER);
        this.add(checkBox, BaseViewConstraint.CENTER);

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
