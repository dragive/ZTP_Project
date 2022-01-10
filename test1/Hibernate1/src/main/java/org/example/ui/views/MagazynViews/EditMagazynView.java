package org.example.ui.views.MagazynViews;

import lombok.Getter;
import lombok.Setter;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class EditMagazynView extends BaseView {
    JLabel opisText = new JLabel("Opis");
    JTextField opis = new JTextField();

    JLabel addressText = new JLabel("Adres");
    JTextField address = new JTextField();

    JLabel cityText = new JLabel("Miasto");
    JTextField city = new JTextField();

    JLabel cinemaText = new JLabel("Nazwa kina");
    JLabel cinema =  new JLabel("----");

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    public EditMagazynView() {
        super(new GridLayout(0,2));

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(opisText,BaseViewConstraint.CENTER);
        this.add(opis,BaseViewConstraint.CENTER);

        this.add(addressText,BaseViewConstraint.CENTER);
        this.add(address,BaseViewConstraint.CENTER);

        this.add(cityText,BaseViewConstraint.CENTER);
        this.add(city,BaseViewConstraint.CENTER);

        this.add(cinemaText,BaseViewConstraint.CENTER);
        this.add(cinema,BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
