package org.example.ui.views.KontrahentViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KontrahentEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class EditKontrahentView extends BaseView {
    JLabel companyNameText = new JLabel("Nazwa firmy");
    JTextField companyName = new JTextField();

    JLabel addressText = new JLabel("Adres");
    JTextField address = new JTextField();

    JLabel cityText = new JLabel("Miasto");
    JTextField city = new JTextField();

    JLabel NIPText = new JLabel("NIP");
    JTextField NIP = new JTextField();

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    public EditKontrahentView(KontrahentEntity kontrahent) {
        super(new GridLayout(0,2));

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(companyNameText, BaseViewConstraint.CENTER);
        this.add(companyName, BaseViewConstraint.CENTER);

        this.add(NIPText, BaseViewConstraint.CENTER);
        this.add(NIP, BaseViewConstraint.CENTER);

        this.add(addressText, BaseViewConstraint.CENTER);
        this.add(address, BaseViewConstraint.CENTER);

        this.add(cityText, BaseViewConstraint.CENTER);
        this.add(city, BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
