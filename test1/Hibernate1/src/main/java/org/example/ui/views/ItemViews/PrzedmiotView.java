package org.example.ui.views.ItemViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.entities.PrzedmiotEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class PrzedmiotView extends BaseView {
    private PrzedmiotEntity przedmiotEntity;

    JLabel productNameLabel = new JLabel("Nazwa przedmiotu");
    JLabel productNameField;

    JLabel productCategoryLabel = new JLabel("Kategoria przedmiotu");
    JLabel productCategoryField;

    JLabel productPriceNettoLabel = new JLabel("Cena netto");
    JLabel productPriceNettoField;

    JLabel productPriceBruttoLabel = new JLabel("Cena brutto");
    JLabel productPriceBruttoField;

    JLabel productTaxLabel = new JLabel("Podatek");
    JLabel productTaxField;

    JLabel productCzyNaWynosLabel = new JLabel("");
    JLabel productCzyNaWynosField;

    JLabel productProducentLabel = new JLabel("Producent");
    JLabel productProducentField;

    JLabel productDateLabel = new JLabel("Data przydatności (yyyy-MM-hh dd:mm)");
    JLabel productDateField;

    JButton editButton = new JButton("Edytuj");
    JButton deleteButton = new JButton("Usuń");

    JButton exit = new JButton("Wstecz");
    
    public PrzedmiotView(PrzedmiotEntity przedmiotEntity) {
        super(new GridLayout(0,1));
        this.przedmiotEntity = przedmiotEntity;
        
        productNameField = new JLabel(przedmiotEntity.getNazwa());
        productCategoryField = new JLabel(przedmiotEntity.getKategoria());
        productPriceBruttoField = new JLabel(przedmiotEntity.getCena().toString());
        productCzyNaWynosField = new JLabel(przedmiotEntity.getCzyNaWynos().toString());
        productProducentField = new JLabel(przedmiotEntity.getProducent());
        productDateField = new JLabel(przedmiotEntity.getDataWaznosci().toString());

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(productNameLabel, BaseViewConstraint.CENTER);
        this.add(productNameField, BaseViewConstraint.CENTER);

        this.add(productCategoryLabel, BaseViewConstraint.CENTER);
        this.add(productCategoryField, BaseViewConstraint.CENTER);

        this.add(productPriceBruttoLabel, BaseViewConstraint.CENTER);
        this.add(productPriceBruttoField, BaseViewConstraint.CENTER);

        this.add(productCzyNaWynosLabel, BaseViewConstraint.CENTER);
        this.add(productCzyNaWynosField, BaseViewConstraint.CENTER);

        this.add(productProducentLabel, BaseViewConstraint.CENTER);
        this.add(productProducentField, BaseViewConstraint.CENTER);

        this.add(productDateLabel, BaseViewConstraint.CENTER);
        this.add(productDateField, BaseViewConstraint.CENTER);
        

        setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);

        this.add(editButton,BaseViewConstraint.FOOTER);
        this.add(deleteButton,BaseViewConstraint.FOOTER);

        this.add(exit,BaseViewConstraint.FOOTER);
        
        
    }
}
