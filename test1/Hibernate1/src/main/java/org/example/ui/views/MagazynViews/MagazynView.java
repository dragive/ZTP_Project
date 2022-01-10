package org.example.ui.views.MagazynViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.MagazynEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MagazynView extends BaseView {
    JLabel opisText = new JLabel("Opis");
    JLabel opis;

    JLabel addressText = new JLabel("Adres");
    JLabel address;

    JLabel cityText = new JLabel("Miasto");
    JLabel city;

    JLabel cinemaText = new JLabel("Nazwa kina");
    JLabel cinema;

    JButton buyProduct = new JButton("Sprzedaż produktu");
    JButton productTransactions = new JButton("Sprawdź transakcje");
    JButton exit = new JButton("Wstecz");
    JButton edit = new JButton("Edytuj");
    JButton editItemsInStorage = new JButton("Edytuj stan magazynu");
    JButton delete = new JButton("Usuń");

    public MagazynView(MagazynEntity magazynEntity) {
        super(new GridLayout(0,2));

        opis = new JLabel(magazynEntity.getOpis());
        address = new JLabel(magazynEntity.getAdres());
        city = new JLabel(magazynEntity.getMiasto());
        cinema = new JLabel(magazynEntity.getKino().getName());

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(opisText, BaseViewConstraint.CENTER);
        this.add(opis, BaseViewConstraint.CENTER);

        this.add(addressText, BaseViewConstraint.CENTER);
        this.add(address, BaseViewConstraint.CENTER);

        this.add(cityText, BaseViewConstraint.CENTER);
        this.add(city, BaseViewConstraint.CENTER);

        setLayout(new GridLayout(3,2),BaseViewConstraint.FOOTER);
        this.add(buyProduct, BaseViewConstraint.FOOTER);
        this.add(productTransactions, BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
        this.add(edit,BaseViewConstraint.FOOTER);
        this.add(editItemsInStorage,BaseViewConstraint.FOOTER);
        this.add(delete,BaseViewConstraint.FOOTER);
    }
}
