package org.example.ui.views.FilmViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.FilmController;
import org.example.jpa.entities.FilmEntity;
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
public class FilmListView extends BaseView {
    JButton addFilm = new JButton("Dodaj film");
    JLabel message = new JLabel("Wybierz film z listy:");

    public FilmListView(List<FilmEntity> filmEntityList) {
        super(new GridLayout(0,1));

        mountPanels();

        this.add(message, BaseViewConstraint.HEADER);
        for(FilmEntity film:filmEntityList) {
            JButton temp = new JButton(film.getTitle());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    FilmController filmController = FilmController.getInstance(frame);
                    filmController.details(film);
                }
            });
            this.add(temp, BaseViewConstraint.NORTH);
        }
        if(MenuPanel.user instanceof PracownikEntity) this.add(addFilm, BaseViewConstraint.FOOTER);
    }
}
