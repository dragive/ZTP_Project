package org.example.ui.views.MagazynViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.MagazynController;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.repositories.KinoRepository;
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
public class AddMagazynView extends BaseView {
    KinoEntity kino;

    JLabel opisText = new JLabel("Opis");
    JTextField opis = new JTextField();

    JLabel addressText = new JLabel("Adres");
    JTextField address = new JTextField();

    JLabel cityText = new JLabel("Miasto");
    JTextField city = new JTextField();

    JLabel cinemaText = new JLabel("Nazwa kina");
    JButton cinemaSelect = new JButton("Wybierz Kino");

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    JLabel nameOfCinema = new JLabel("");

    public AddMagazynView() {
        super(new GridLayout(0,2));

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(opisText, BaseViewConstraint.CENTER);
        this.add(opis, BaseViewConstraint.CENTER);

        this.add(addressText, BaseViewConstraint.CENTER);
        this.add(address, BaseViewConstraint.CENTER);

        this.add(cityText, BaseViewConstraint.CENTER);
        this.add(city, BaseViewConstraint.CENTER);

        this.add(cinemaText, BaseViewConstraint.CENTER);
        this.add(cinemaSelect, BaseViewConstraint.CENTER);

        this.add(new JPanel(), BaseViewConstraint.CENTER);
        this.add(nameOfCinema, BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);


        cinemaSelect.addActionListener(new editItemSelectedActionListener(this));
    }



    public class editItemSelectedActionListener implements ActionListener {

        AddMagazynView addMagazynView;
        editItemSelectedActionListener(AddMagazynView addMagazynView){
            this.addMagazynView = addMagazynView;
        }


        @Override
        public void actionPerformed(ActionEvent e) {

            JDialog jDialog =new JDialog(frame,"Wybierz Kino z listy");
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
            List<KinoEntity> kinoEntities = KinoRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getAll().stream().sorted(Comparator.comparing(KinoEntity::getName)).collect(Collectors.toList());
            for(int i =0;i<10;i++){
                for (KinoEntity kinoEntity : kinoEntities) {

                    JButton temp = new JButton(kinoEntity.getName());
                    panel.add(temp);

                    temp.addActionListener(new tempActionListener(addMagazynView,kinoEntity, jDialog));
                }
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
        KinoEntity kinoEntity;
        JDialog jDialog;
        AddMagazynView addMagazynView;
        tempActionListener(AddMagazynView addMagazynView,KinoEntity kinoEntity,JDialog jDialog){
            this.addMagazynView =addMagazynView;
            this.kinoEntity = kinoEntity;
            this.jDialog = jDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            jDialog.setVisible(false);

            MagazynController.getInstance(frame).createRepaint(kinoEntity, addMagazynView);
        }
    }



}
