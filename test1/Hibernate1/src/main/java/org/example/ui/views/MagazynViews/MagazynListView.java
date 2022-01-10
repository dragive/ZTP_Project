package org.example.ui.views.MagazynViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.MagazynController;
import org.example.jpa.entities.MagazynEntity;
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
public class MagazynListView extends BaseView {
    JButton addMagazyn = new JButton("Dodaj magazyn");
    JLabel message = new JLabel("Wybierz magazyn z listy:");

    public MagazynListView(List<MagazynEntity> magazynEntityList) {
        super(new GridLayout(0,1));

        mountPanels();
//        for(int i =0;i<10;i++) {
//            this.add(new JLabel("1." + i),BaseViewConstraint.HEADER);
//            this.add(new JLabel("2." + i),BaseViewConstraint.NORTH);
//            this.add(new JLabel("3." + i),BaseViewConstraint.CENTER);
//            this.add(new JLabel("4." + i),BaseViewConstraint.FOOTER);
//        }
//
        this.add(message, BaseViewConstraint.HEADER);
        for(MagazynEntity magazyn:magazynEntityList) {
            JButton temp = new JButton("Magazyn nr "+magazyn.getId()+" - "+magazyn.getKino().getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    MagazynController magazynController = MagazynController.getInstance(frame);
                    magazynController.details(magazyn);
                }
            });
            this.add(temp,BaseViewConstraint.NORTH);
        }
        if(MenuPanel.user instanceof PracownikEntity) this.add(addMagazyn,BaseViewConstraint.FOOTER);
    }
}
