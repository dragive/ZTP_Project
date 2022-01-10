package org.example.ui.views.TransactionViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.SalaEntity;
import org.example.jpa.entities.SeansEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddReservationTransactionView extends BaseView {
    JLabel userText = new JLabel("Użytkownik");
    JLabel user = new JLabel();

    JLabel priceText = new JLabel("Cena");
    JTextField price;

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    JRadioButton karta = new JRadioButton("Karta");
    JRadioButton gotowka = new JRadioButton("Gotówka");

    ButtonGroup group = new ButtonGroup();

    private RezerwacjaEntity rezerwacja;
    private Object previousWindow;

    public AddReservationTransactionView(RezerwacjaEntity rezerwacja, Object previousWindow) {
        super(new GridLayout(0,2));
        mountPanels();
        this.rezerwacja = rezerwacja;
        this.previousWindow = previousWindow;
        gotowka.setSelected(true);
        group.add(karta);
        group.add(gotowka);

        setLayout(new GridLayout(0,2),BaseViewConstraint.CENTER);
        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        user.setText(rezerwacja.getKlient().getLogin());
        price = new JTextField();

        this.add(userText, BaseViewConstraint.CENTER);
        this.add(user, BaseViewConstraint.CENTER);

        this.add(priceText, BaseViewConstraint.CENTER);
        this.add(price, BaseViewConstraint.CENTER);

        this.add(karta, BaseViewConstraint.CENTER);
        this.add(gotowka, BaseViewConstraint.CENTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
