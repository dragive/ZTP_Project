package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.User;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class KlientReservations extends BaseView {

    JButton exit = new JButton("Wstecz");

    JLabel reservationNameText = new JLabel("Nazwa: ");
    JLabel reservationName = new JLabel();

    JLabel dateText = new JLabel("Data utworzenia: ");
    JLabel date = new JLabel();

    JCheckBox checkBox = new JCheckBox("Opłacona");

    public KlientReservations(List<JButton> rezerwacjaEntityList) {
        super(new GridLayout(0, 1));
        mountPanels();

        for(JButton rezerwacja: rezerwacjaEntityList) {
            this.add(rezerwacja, BaseViewConstraint.NORTH);
        }

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
