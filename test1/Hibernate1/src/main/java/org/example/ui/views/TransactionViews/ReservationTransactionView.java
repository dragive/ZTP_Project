package org.example.ui.views.TransactionViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.RezerwacjaTransakcjaEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ReservationTransactionView extends BaseView {
    JLabel userText = new JLabel("Użytkownik");
    JLabel user = new JLabel();

    JLabel priceText = new JLabel("Cena");
    JLabel price;

    JButton exit = new JButton("Wstecz");

    JRadioButton karta = new JRadioButton("Karta");
    JRadioButton gotowka = new JRadioButton("Gotówka");

    ButtonGroup group = new ButtonGroup();


    public ReservationTransactionView(RezerwacjaTransakcjaEntity rezerwacjaTransakcja) {
        super(new GridLayout(0,2));

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        gotowka.setSelected(rezerwacjaTransakcja.getCzyGotowka());
        karta.setSelected(rezerwacjaTransakcja.getCzyKarta());
        group.add(karta);
        group.add(gotowka);
        karta.setEnabled(false);
        gotowka.setEnabled(false);

        user.setText(rezerwacjaTransakcja.getKlient().getLogin());
        price = new JLabel(rezerwacjaTransakcja.getCena().toString());

        this.add(userText,BaseViewConstraint.CENTER);
        this.add(user,BaseViewConstraint.CENTER);

        this.add(priceText,BaseViewConstraint.CENTER);
        this.add(price,BaseViewConstraint.CENTER);

        this.add(karta,BaseViewConstraint.CENTER);
        this.add(gotowka,BaseViewConstraint.CENTER);

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
