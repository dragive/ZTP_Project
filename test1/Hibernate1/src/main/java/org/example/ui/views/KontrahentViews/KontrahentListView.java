package org.example.ui.views.KontrahentViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.KontrahentController;
import org.example.jpa.entities.KontrahentEntity;
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
public class KontrahentListView extends BaseView {
    JButton addKontrahent = new JButton("Dodaj kontrahenta");
    JLabel message = new JLabel("Wybierz kontrahenta z listy:");

    public KontrahentListView(List<KontrahentEntity> kontrahentEntityList) {
        super(new GridLayout(0,1));
        mountPanels();
        this.add(message, BaseViewConstraint.HEADER);
        for(KontrahentEntity kontrahent:kontrahentEntityList) {
            JButton temp = new JButton(kontrahent.getNazwaFirmy());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    KontrahentController kontrahentController = KontrahentController.getInstance(frame);
                    kontrahentController.details(kontrahent);
                }
            });
            this.add(temp,BaseViewConstraint.NORTH);
        }
        if(MenuPanel.user instanceof PracownikEntity)
            this.add(addKontrahent,BaseViewConstraint.FOOTER);
    }

}
