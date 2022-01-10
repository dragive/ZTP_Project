package org.example.ui.views.CinemaViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.CinemaController;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.entities.PracownikEntity;
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
public class CinemaListView extends BaseView {
    JButton addCinema = new JButton("Dodaj kino");
    JLabel message;
    JButton temp;

    public CinemaListView(List<KinoEntity> kinoEntityList) {
        super(new GridLayout(0,1));
        MenuPanel.bottomPanel=this;
        if (MenuPanel.user instanceof PracownikEntity) message = new JLabel("Wybierz kino lub utwórz nowe:");
        else message = new JLabel("Wybierz kino:");

        mountPanels();

        this.add(message, BaseViewConstraint.HEADER);

        for(KinoEntity kinoEntity: kinoEntityList) {
            temp = new JButton(kinoEntity.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    CinemaController cinemaController = CinemaController.getInstance(frame);
                    cinemaController.details(kinoEntity);
                }
            });
            this.add(temp, BaseViewConstraint.NORTH);
        }

        if (MenuPanel.user instanceof PracownikEntity) {
//            this.add(new JLabel(""));
            this.add(addCinema, BaseViewConstraint.FOOTER);
        }
    }
}
