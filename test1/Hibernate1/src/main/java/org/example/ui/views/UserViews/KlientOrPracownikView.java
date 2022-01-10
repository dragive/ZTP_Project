package org.example.ui.views.UserViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KlientEntity;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.User;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class KlientOrPracownikView extends BaseView {


    JButton klient = new JButton("Klient");
    JButton pracownik = new JButton("Pracownik");
    JButton exit = new JButton("Wstecz");

    public KlientOrPracownikView() {
        super(new GridLayout(0, 2));
        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(klient,BaseViewConstraint.CENTER);
        this.add(pracownik,BaseViewConstraint.CENTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
