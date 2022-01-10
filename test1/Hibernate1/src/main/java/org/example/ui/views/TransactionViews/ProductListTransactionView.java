package org.example.ui.views.TransactionViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.PrzedmiotMagazynEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class ProductListTransactionView extends BaseView {

    JButton addProduct = new JButton("Dodaj produkt");
    JButton accept = new JButton("Stwórz transakcję");
    JButton exit = new JButton("Exit");

    JLabel message = new JLabel("Lista produktów:");

    private PrzedmiotMagazynEntity product;


    public ProductListTransactionView(List<PrzedmiotMagazynEntity> products) {
        super(new GridLayout(0, 1));
        mountPanels();
        this.add(message, BaseViewConstraint.HEADER);
        for (PrzedmiotMagazynEntity product : products) {
            this.add(new JLabel(product.getPrzedmiot().getNazwa() + " " + product.getIlosc()), BaseViewConstraint.NORTH);
        }

        setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);
        this.add(addProduct,BaseViewConstraint.FOOTER);
        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
