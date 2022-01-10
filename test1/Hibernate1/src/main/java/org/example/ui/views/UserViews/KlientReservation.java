package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KlientEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.User;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.MenuPanel;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class KlientReservation extends BaseView {
    JButton exit = new JButton("Wstecz");

    JLabel reservationNameText = new JLabel("Nazwa: ");
    JLabel reservationName = new JLabel();

    JLabel dateText = new JLabel("Data utworzenia: ");
    JLabel date = new JLabel();

    JCheckBox checkBox = new JCheckBox("Opłacona");
    JButton payment = new JButton("Opłać");

    RezerwacjaEntity rezerwacja;
    public KlientReservation(RezerwacjaEntity rezerwacja) {
        super(new GridLayout(0, 2));

        mountPanels();

        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.rezerwacja=rezerwacja;

        reservationName.setText(rezerwacja.getNazwa());
        date.setText(rezerwacja.getDataRezerwacji().toString());
        checkBox.setSelected(rezerwacja.getCzyOplacona());
        checkBox.setEnabled(false);

        this.add(reservationNameText,BaseViewConstraint.CENTER);
        this.add(reservationName,BaseViewConstraint.CENTER);

        this.add(dateText,BaseViewConstraint.CENTER);
        this.add(date,BaseViewConstraint.CENTER);

        this.add(checkBox,BaseViewConstraint.CENTER);


        if(MenuPanel.user instanceof PracownikEntity && !checkBox.isSelected()) {
            setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

            this.add(payment,BaseViewConstraint.FOOTER);
            this.add(exit,BaseViewConstraint.FOOTER);
        }
        else{

            this.add(exit,BaseViewConstraint.FOOTER);
        }
    }
}
