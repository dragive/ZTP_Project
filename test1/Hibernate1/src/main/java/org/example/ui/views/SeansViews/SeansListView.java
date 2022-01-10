package org.example.ui.views.SeansViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.SeansController;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.SeansEntity;
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
public class SeansListView extends BaseView {
    JButton addSeans = new JButton("Dodaj seans");
    JButton exit = new JButton("Wstecz");
    JLabel message = new JLabel("Wybierz seans z listy:");

    public SeansListView(List<SeansEntity> seansEntityList) {
        super(new GridLayout(0,1));
        mountPanels();

        this.add(message, BaseViewConstraint.HEADER);
        for(SeansEntity seans:seansEntityList) {
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
                    SeansController seansController = SeansController.getInstance(frame);
                    seansController.details(seans);
                }
            });
            this.add(temp,BaseViewConstraint.NORTH);
        }
        if(MenuPanel.user instanceof PracownikEntity) {
            JPanel container = new JPanel((new GridLayout(1,2)));
            container.add(addSeans);
            container.add(exit);

            this.add(container,BaseViewConstraint.FOOTER);
        }

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
