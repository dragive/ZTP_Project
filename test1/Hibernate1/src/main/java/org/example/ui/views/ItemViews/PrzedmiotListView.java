package org.example.ui.views.ItemViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.ItemController;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.PrzedmiotEntity;
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
public class PrzedmiotListView extends BaseView {

    JButton addPrzedmiot = new JButton("Dodaj przedmiot");
    JLabel message = new JLabel("Wybierz przedmiot z listy:");

    public PrzedmiotListView(List<PrzedmiotEntity> przedmiotEntityList) {
        super(new GridLayout(0,1));
        mountPanels();

        this.add(message, BaseViewConstraint.HEADER);
        for(PrzedmiotEntity przedmiot:przedmiotEntityList) {
            JButton temp = new JButton(przedmiot.getNazwa());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    ItemController itemController = ItemController.getInstance(frame);
                    itemController.details(przedmiot);
                }
            });
            this.add(temp, BaseViewConstraint.NORTH);
        }
        if(MenuPanel.user instanceof PracownikEntity)
            this.add(addPrzedmiot, BaseViewConstraint.FOOTER);
    }
}
