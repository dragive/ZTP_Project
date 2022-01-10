package org.example.ui.views.SeansViews;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.jpa.controllers.SeansController;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.SeansEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Getter
@Setter
public class SeansAddFilmView extends BaseView {
    JButton exit = new JButton("Wstecz");
    JLabel message = new JLabel("Wybierz film z listy:");

    public SeansAddFilmView(SeansEntity seans, List<FilmEntity> filmEntityList) {
        super(new GridLayout(0,1));
        mountPanels();

        this.add(message,BaseViewConstraint.HEADER);
        for(FilmEntity film:filmEntityList) {
            JButton temp = new JButton(film.getTitle());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    SeansController seansController = SeansController.getInstance(frame);
                    seansController.create(seans,film);
                }
            });
            this.add(temp, BaseViewConstraint.NORTH);
        }

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
