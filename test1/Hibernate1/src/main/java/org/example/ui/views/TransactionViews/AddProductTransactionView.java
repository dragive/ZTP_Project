package org.example.ui.views.TransactionViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.PrzedmiotEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.ItemViews.PrzedmiotView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class AddProductTransactionView extends BaseView {
    JLabel kontrahentText = new JLabel("Kontrahent");
    JTextField kontrahent;

    JLabel priceText = new JLabel("Cena");
    JTextField price;

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    JRadioButton karta = new JRadioButton("Karta");
    JRadioButton gotowka = new JRadioButton("Gotówka");

    ButtonGroup group = new ButtonGroup();

    JCheckBox faktura = new JCheckBox("Czy faktura");

    public AddProductTransactionView() {
        super(new GridLayout(0,2));

        gotowka.setSelected(true);
        group.add(karta);
        group.add(gotowka);

        price = new JTextField();
        kontrahent = new JTextField();

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);
        setLayout(new GridLayout(0,3), BaseViewConstraint.FOOTER);

        this.add(kontrahentText,BaseViewConstraint.CENTER);
        this.add(kontrahent,BaseViewConstraint.CENTER);

        this.add(priceText,BaseViewConstraint.CENTER);
        this.add(price,BaseViewConstraint.CENTER);

        this.add(karta,BaseViewConstraint.CENTER);
        this.add(gotowka,BaseViewConstraint.CENTER);

        this.add(faktura,BaseViewConstraint.CENTER);
        this.add(new JLabel(""),BaseViewConstraint.CENTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);

    }
}
