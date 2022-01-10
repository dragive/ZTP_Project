package org.example.ui.views.MagazynViews;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.controllers.MagazynController;
import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.PrzedmiotEntity;
import org.example.jpa.repositories.PrzedmiotRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public class AddItemMagazynView extends BaseView {

    PrzedmiotEntity przedmiotEntity;

    JButton exit = new JButton("Wstecz");
    JButton accept = new JButton("Zaakceptuj");

    JLabel newItemLabel = new JLabel("Wybierz nowy przedmiot:");
    JButton newItemButton = new JButton("Wybierz");


    JLabel chosenItem = new JLabel("");
    JLabel empty = new JLabel("");

    JLabel amountLabel = new JLabel("Wybierz ilość:");
    JTextField amountTextField = new JTextField();

    MagazynEntity magazyn;

    public AddItemMagazynView(MagazynEntity magazynEntity) {
        super(new GridLayout(0,2));

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.magazyn = magazynEntity;

        newItemButton.addActionListener(new editItemSelectedActionListener(this));

        this.add(newItemLabel, BaseViewConstraint.CENTER);
        this.add(newItemButton, BaseViewConstraint.CENTER);

        this.add(chosenItem, BaseViewConstraint.CENTER);
        this.add(empty, BaseViewConstraint.CENTER);

        this.add(amountLabel, BaseViewConstraint.CENTER);
        this.add(amountTextField, BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
}




    public class editItemSelectedActionListener implements ActionListener{

        AddItemMagazynView addItemMagazynView;
        editItemSelectedActionListener(AddItemMagazynView addItemMagazynView){
            this.addItemMagazynView =addItemMagazynView;
        }


        @Override
        public void actionPerformed(ActionEvent e) {

            JDialog jDialog =new JDialog(frame,"Wybierz przedmiot z listy");
            JPanel panelContainer = new JPanel(new BorderLayout());

            JPanel panel = new JPanel(new GridLayout(0,1));

            JPanel borderLayout = new JPanel(new BorderLayout());
            borderLayout.add(panel,BorderLayout.NORTH);


            JScrollPane jScrollPane = new JScrollPane(borderLayout);
            jScrollPane.getVerticalScrollBar().setUnitIncrement(16);


            panelContainer.add(jScrollPane ,BorderLayout.CENTER);


//            JButton submit = new JButton("Ok");
            JButton cancel = new JButton("Anuluj");


            ///////
            List<PrzedmiotEntity> przedmiotEntities =PrzedmiotRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getAll().stream().sorted(Comparator.comparing(PrzedmiotEntity::getNazwa)).collect(Collectors.toList());

            for (PrzedmiotEntity przedmiotEntity : przedmiotEntities) {

                JButton temp = new JButton(przedmiotEntity.getNazwa());
                panel.add(temp);

                temp.addActionListener(new tempActionListener(addItemMagazynView,przedmiotEntity, jDialog));
            }

            JPanel buttons = new JPanel(new GridLayout(1,1));

            buttons.add(cancel);
            panelContainer.add(buttons,BorderLayout.SOUTH);

            jScrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            jDialog.add(panelContainer);

            jDialog.setVisible(true);
            jDialog.requestFocus();
            jDialog.setSize(280,200);
            jDialog.setLocationRelativeTo(null);


            cancel.addActionListener(e1 -> { jDialog.setVisible(false);});




        }


    }

    class tempActionListener implements ActionListener{
        PrzedmiotEntity przedmiotEntityInternal;
        JDialog jDialog;
        AddItemMagazynView addItemMagazynView;
        tempActionListener(AddItemMagazynView addItemMagazynView,PrzedmiotEntity przedmiotEntityParam,JDialog jDialog){
            this.addItemMagazynView =addItemMagazynView;
            this.przedmiotEntityInternal = przedmiotEntityParam;
            this.jDialog = jDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            przedmiotEntity = przedmiotEntityInternal;
            jDialog.setVisible(false);

            MagazynController.getInstance(frame).addNewItemRepaint(magazyn,addItemMagazynView.getAmountTextField().getText());
        }
    }




}
