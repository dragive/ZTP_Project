package org.example.ui.views.CinemaViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KinoEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class EditCinemaView extends BaseView {
    JTextField cinemaName = new JTextField();
    JLabel cinemaNameText = new JLabel("Nazwa kina");


    JTextField desc = new JTextField();
    JLabel descText = new JLabel("Opis kina");


    JTextField adres = new JTextField();
    JLabel adresText = new JLabel("Adres kina");

    JTextField miasto = new JTextField();
    JLabel miastoText = new JLabel("Miasto kina");

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");
    public EditCinemaView() {
        super(new GridLayout(0,2));

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(cinemaNameText, BaseViewConstraint.CENTER);
        this.add(cinemaName, BaseViewConstraint.CENTER);

        this.add(descText, BaseViewConstraint.CENTER);
        this.add(desc, BaseViewConstraint.CENTER);

        this.add(adresText, BaseViewConstraint.CENTER);
        this.add(adres, BaseViewConstraint.CENTER);

        this.add(miastoText, BaseViewConstraint.CENTER);
        this.add(miasto, BaseViewConstraint.CENTER);


        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
