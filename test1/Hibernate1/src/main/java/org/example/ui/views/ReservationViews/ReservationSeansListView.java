package org.example.ui.views.ReservationViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.ReservationController;
import org.example.jpa.entities.*;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Getter
@Setter
public class ReservationSeansListView extends BaseView {

    JButton exit = new JButton("Wstecz");
    JLabel message = new JLabel("Wybierz seans:");

    public ReservationSeansListView(List<SeansEntity> seansList) {
        super(new GridLayout(0,1));

        mountPanels();

        this.add(message, BaseViewConstraint.HEADER);

        for(SeansEntity seans: seansList) {
            List<FilmEntity> tempList = seans.getSeansFilms();
            StringBuilder stringBuilder = new StringBuilder();
            for(FilmEntity filmEntity:tempList) {
                stringBuilder.append(filmEntity.getTitle()+" ");
            }
            stringBuilder.append(seans.getGodzinaRozpoczecia().toString() + " - " + seans.getGodzinaZakonczenia());
            JButton temp = new JButton(stringBuilder.toString());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    ReservationController.getInstance(frame).seatsList(seans,seans.getSala().getMiejscWRzedzie().intValue());

                    //frame.setTitle("Podgląd sali");
                }
            });
            this.add(temp,BaseViewConstraint.NORTH);
        }

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
