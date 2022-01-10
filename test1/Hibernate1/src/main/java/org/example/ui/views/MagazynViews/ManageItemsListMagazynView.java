package org.example.ui.views.MagazynViews;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.controllers.MagazynController;
import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;
import org.example.jpa.repositories.MagazynRepository;
import org.example.jpa.repositories.PrzedmiotMagazynRepository;
import org.example.services.DatabaseService;
import org.example.ui.Throwable.RepaintRequest;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Slf4j
public class ManageItemsListMagazynView extends BaseView {

    JButton exit = new JButton("Wstecz");
    JButton addNewItem = new JButton("Dodaj nowy przedmiot");

    List<Component> linkedList = new LinkedList<>();

    public ManageItemsListMagazynView(MagazynEntity magazynEntity) {
        super(new GridLayout(0,2));
        mountPanels();
        pain(magazynEntity);

    }

    private void pain(MagazynEntity magazynEntity){
        List<PrzedmiotMagazynEntity> list = MagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById(magazynEntity.getId()).getPrzedmiotyWMagazynie();
        for (PrzedmiotMagazynEntity przedmiotMagazynEntity: list) {
            JButton editAmountButton = new JButton(przedmiotMagazynEntity.getPrzedmiot().getNazwa());
            JLabel  editAmountLabel = new JLabel(przedmiotMagazynEntity.getIlosc().toString());

            editAmountButton.addActionListener( new editAmountButtonActionListener(przedmiotMagazynEntity));
            JPanel container = new JPanel(new GridLayout(1,2));

            container.add(editAmountButton);
            container.add(editAmountLabel);
            this.add(container, BaseViewConstraint.NORTH);

        }
        JPanel container = new JPanel(new GridLayout(1,2));

        container.add(addNewItem);
        container.add(exit);

        this.add(container,BaseViewConstraint.FOOTER);
    }

    public class editAmountButtonActionListener implements ActionListener{
        PrzedmiotMagazynEntity przedmiotMagazynEntity;
        public editAmountButtonActionListener(PrzedmiotMagazynEntity przedmiotMagazynEntity){
            this.przedmiotMagazynEntity = przedmiotMagazynEntity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            log.error(przedmiotMagazynEntity.toString());
            JDialog jDialog =new JDialog(frame,"Zmiana wartości");
            JPanel panelContainer = new JPanel(new BorderLayout());

            JPanel panel = new JPanel(new GridLayout(0,1));
            panelContainer.add(panel,BorderLayout.CENTER);

            JLabel jLabel = new JLabel("Podaj ilość do aktulizacji: ");
            JLabel empty = new JLabel("");
            JTextField jTextField = new JTextField();

            JButton submit = new JButton("Ok");
            JButton cancel = new JButton("Anuluj");

            panel.add(jLabel);
            panel.add(jTextField);
            panel.add(empty);

            JPanel buttons = new JPanel(new GridLayout(1,2));

            buttons.add(submit);
            buttons.add(cancel);
            panel.add(buttons);

            panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            jDialog.add(panelContainer);

            jDialog.setVisible(true);
            jDialog.requestFocus();
            jDialog.setSize(280,200);
            jDialog.setLocationRelativeTo(null);

            jTextField.setText(przedmiotMagazynEntity.getIlosc().toString());


            cancel.addActionListener(e1 -> { jDialog.setVisible(false);});

            //TODO WALIDACJA

            submit.addActionListener(e1 -> {
                try{
                    przedmiotMagazynEntity.setIlosc(Long.parseLong(jTextField.getText()));
                    PrzedmiotMagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().update(przedmiotMagazynEntity);
                    jDialog.setVisible(false);


                    MagazynController.getInstance(frame).manageItemsOfStorageRepaint(przedmiotMagazynEntity.getMagazyn());
                }
                catch (Exception ex ){

                    //implementacja
                }

            });
        }
    }


}
