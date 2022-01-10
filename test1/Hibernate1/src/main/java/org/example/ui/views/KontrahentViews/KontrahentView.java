package org.example.ui.views.KontrahentViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KontrahentEntity;
import org.example.jpa.entities.MagazynEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class KontrahentView extends BaseView {
    JLabel companyNameText = new JLabel("Nazwa firmy");
    JLabel companyName;

    JLabel NIPText = new JLabel("NIP");
    JLabel NIP;

    JLabel cityText = new JLabel("Miasto");
    JLabel city;

    JLabel addressText = new JLabel("Adres");
    JLabel address;

    JButton editButton = new JButton("Edytuj");
    JButton deleteButton = new JButton("Usuń");

    JButton exit = new JButton("Wstecz");

    public KontrahentView(KontrahentEntity kontrahentEntity) {
        super(new GridLayout(0,2));

        companyName = new JLabel(kontrahentEntity.getNazwaFirmy());
        address = new JLabel(kontrahentEntity.getAdres());
        city = new JLabel(kontrahentEntity.getMiasto());
        NIP = new JLabel(kontrahentEntity.getNip());

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(companyNameText,BaseViewConstraint.CENTER);
        this.add(companyName,BaseViewConstraint.CENTER);

        this.add(NIPText,BaseViewConstraint.CENTER);
        this.add(NIP,BaseViewConstraint.CENTER);

        this.add(addressText,BaseViewConstraint.CENTER);
        this.add(address,BaseViewConstraint.CENTER);

        this.add(cityText,BaseViewConstraint.CENTER);
        this.add(city,BaseViewConstraint.CENTER);
        this.add(editButton,BaseViewConstraint.CENTER);
        this.add(deleteButton,BaseViewConstraint.CENTER);

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
