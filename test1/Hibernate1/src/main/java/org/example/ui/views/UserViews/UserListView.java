package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.UserController;
import org.example.jpa.entities.KlientEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.User;
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
public class UserListView extends BaseView {
    KlientEntity klientEntity;
    PracownikEntity pracownikEntity;

    JButton addUser = new JButton("Dodaj użytkownika");

    public UserListView(List<User> userList) {
        super(new GridLayout(0,1));
        mountPanels();
        MenuPanel.bottomPanel=this;
        for(User item : userList) {
            JButton temp = new JButton();
            if(item instanceof KlientEntity) {

                klientEntity = (KlientEntity) item;
                temp.setText(klientEntity.getLogin());
            }
            else {
                pracownikEntity = (PracownikEntity) item;
                temp.setText(pracownikEntity.getLogin());
            }
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(mainContainerPanel);
                    frame.remove(mainContainerPanel);
                    UserController userController = UserController.getInstance(frame);
                    userController.details(item);
                }
            });
            this.add(temp, BaseViewConstraint.NORTH);
        }
        this.add(addUser,BaseViewConstraint.FOOTER);
    }
}
