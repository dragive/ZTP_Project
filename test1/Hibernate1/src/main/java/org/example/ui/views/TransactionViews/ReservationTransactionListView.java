package org.example.ui.views.TransactionViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class ReservationTransactionListView extends BaseView {

    private List<JButton> rezerwacjaEntityList;

    JButton exit = new JButton("Wstecz");

    public ReservationTransactionListView(List<JButton> rezerwacjaEntityList) {
        super(new GridLayout(0,1));

        mountPanels();
        this.rezerwacjaEntityList = rezerwacjaEntityList;

        for(JButton rezerwacja: rezerwacjaEntityList) {
            this.add(rezerwacja, BaseViewConstraint.NORTH);
        }

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
