package org.example.ui.views.RoomViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.RoomController;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.SalaEntity;
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
public class RoomListView  extends BaseView {
    JButton addRoom = new JButton("Dodaj salę");
    JButton exit = new JButton("Wstecz");
    JLabel message = new JLabel("Wybierz salę z listy:");

    public RoomListView(List<SalaEntity> roomEntityList) {
        super(new GridLayout(0,1));
        mountPanels();
        this.add(message, BaseViewConstraint.HEADER);
        for(SalaEntity room:roomEntityList) {
            JButton temp = new JButton(room.getNazwa());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    RoomController roomController = RoomController.getInstance(frame);
                    roomController.details(room);
                }
            });
            this.add(temp,BaseViewConstraint.NORTH);
        }
        if(MenuPanel.user instanceof PracownikEntity) {
            JPanel container = new JPanel(new GridLayout(1,2));
            this.add(container,BaseViewConstraint.FOOTER);
            container.add(addRoom);
            container.add(exit);
        }
        this.add(exit,BaseViewConstraint.FOOTER);

    }

}
