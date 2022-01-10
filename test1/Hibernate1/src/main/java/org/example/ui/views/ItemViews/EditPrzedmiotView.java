package org.example.ui.views.ItemViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.PrzedmiotEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class EditPrzedmiotView extends BaseView {

    PrzedmiotEntity przedmiotEntity;

    JLabel productNameLabel = new JLabel("Nazwa przedmiotu");
    JTextField productNameField = new JTextField();

    JLabel productCategoryLabel = new JLabel("Kategoria przedmiotu");
    JTextField productCategoryField = new JTextField();

    JLabel productPriceNettoLabel = new JLabel("Cena netto");
    JTextField productPriceNettoField = new JTextField();

    JLabel productPriceBruttoLabel = new JLabel("Cena brutto");
    JTextField productPriceBruttoField = new JTextField();

    JLabel productTaxLabel = new JLabel("Podatek");
    JTextField productTaxField = new JTextField();

    JLabel productCzyNaWynosLabel = new JLabel("");
    JCheckBox productCzyNaWynosField = new JCheckBox("Czy produkt jest \"na wynos\"");

    JLabel productProducentLabel = new JLabel("Producent");
    JTextField productProducentField = new JTextField();

    JLabel productDateLabel = new JLabel("Data przydatności (yyyy-MM-hh dd:mm)");
    JTextField productDateField = new JTextField();

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    public EditPrzedmiotView(PrzedmiotEntity przedmiotEntity) {
        super(new GridLayout(0,2));
        this.przedmiotEntity = przedmiotEntity;

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(productNameLabel , BaseViewConstraint.CENTER);
        this.add(productNameField, BaseViewConstraint.CENTER);

        this.add(productCategoryLabel, BaseViewConstraint.CENTER);
        this.add(productCategoryField, BaseViewConstraint.CENTER);

        this.add(productPriceNettoLabel , BaseViewConstraint.CENTER);

        this.add(productPriceNettoField , BaseViewConstraint.CENTER);
        this.add(productPriceBruttoLabel , BaseViewConstraint.CENTER);
        this.add(productPriceBruttoField , BaseViewConstraint.CENTER);

        this.add(productTaxLabel , BaseViewConstraint.CENTER);
        this.add(productTaxField , BaseViewConstraint.CENTER);

        this.add(productCzyNaWynosLabel , BaseViewConstraint.CENTER);
        this.add(productCzyNaWynosField , BaseViewConstraint.CENTER);

        this.add(productProducentLabel , BaseViewConstraint.CENTER);
        this.add(productProducentField , BaseViewConstraint.CENTER);

        this.add(productDateLabel , BaseViewConstraint.CENTER);
        this.add(productDateField , BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
