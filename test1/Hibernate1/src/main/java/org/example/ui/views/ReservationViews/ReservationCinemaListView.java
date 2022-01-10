package org.example.ui.views.ReservationViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.ReservationController;
import org.example.jpa.entities.KinoEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Getter
@Setter
public class ReservationCinemaListView extends BaseView {
    JLabel message = new JLabel("Wybierz kino by móc umieścić rezerwację w jednym z jego seansów:");
    JButton temp;

    public ReservationCinemaListView(List<KinoEntity> kinoEntityList) {
        super(new GridLayout(0,1));
        MenuPanel.bottomPanel=this;
        mountPanels();
        this.add(message, BaseViewConstraint.HEADER);

        for(KinoEntity kinoEntity: kinoEntityList) {
            temp = new JButton(kinoEntity.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    ReservationController reservationController = ReservationController.getInstance(frame);
                    reservationController.seansList(kinoEntity);
                }
            });
            this.add(temp,BaseViewConstraint.NORTH);
        }
    }
}
