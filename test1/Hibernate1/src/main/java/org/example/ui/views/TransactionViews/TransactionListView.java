package org.example.ui.views.TransactionViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.PrzedmiotEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;
import org.example.jpa.entities.PrzedmiotTransakcjaEntity;
import org.example.jpa.repositories.MagazynRepository;
import org.example.jpa.repositories.ProductTransactionRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.util.List;
@Getter
@Setter
public class TransactionListView extends BaseView {

    JButton exit = new JButton("Exit");

    public TransactionListView(List<PrzedmiotTransakcjaEntity> transakcjaEntityList) {
        super(new GridLayout(0, 1));
        mountPanels();

        for (PrzedmiotTransakcjaEntity transakcjaEntity : transakcjaEntityList) {
            StringBuilder stringBuilder = new StringBuilder();
            for(PrzedmiotEntity przedmiotEntity: transakcjaEntity.getPrzedmiotyTransakcji()) {
                stringBuilder.append(przedmiotEntity.getNazwa()+" ");
            }
            this.add(new JLabel(transakcjaEntity.getId()+" "+stringBuilder.toString()+transakcjaEntity.getCena()), BaseViewConstraint.NORTH);
        }

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
