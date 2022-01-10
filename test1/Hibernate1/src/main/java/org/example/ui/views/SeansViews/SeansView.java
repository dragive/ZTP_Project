package org.example.ui.views.SeansViews;

import com.sun.management.GarbageCollectionNotificationInfo;
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
public class SeansView extends BaseView {
    JLabel seansNameText = new JLabel("Seans");
    JLabel seansName;

    JLabel startDateText = new JLabel("Data rozpoczęcia");
    JLabel startDate;

    JLabel endDateText = new JLabel("Data zakończenia");
    JLabel endDate;

    JButton exit = new JButton("Wstecz");

    public SeansView(SeansEntity seans) {
        super(new GridLayout(0,2));

        List<FilmEntity> tempList = seans.getSeansFilms();
        StringBuilder stringBuilder = new StringBuilder();
        for(FilmEntity filmEntity:tempList) {
            stringBuilder.append(filmEntity.getTitle()+" ");
        }
        stringBuilder.append(seans.getGodzinaRozpoczecia().toString() + " - " + seans.getGodzinaZakonczenia());
        seansName = new JLabel(stringBuilder.toString());

        startDate = new JLabel(seans.getGodzinaRozpoczecia().toString());

        endDate = new JLabel(seans.getGodzinaZakonczenia().toString());

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(seansNameText,BaseViewConstraint.CENTER);
        this.add(seansName,BaseViewConstraint.CENTER);

        this.add(startDateText,BaseViewConstraint.CENTER);
        this.add(startDate,BaseViewConstraint.CENTER);

        this.add(endDateText,BaseViewConstraint.CENTER);
        this.add(endDate,BaseViewConstraint.CENTER);

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
