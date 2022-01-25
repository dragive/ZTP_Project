package org.example.services;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;
import org.example.jpa.repositories.PrzedmiotMagazynRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MagazynQtyObserver extends MagazynObserver{
    List<PrzedmiotMagazynEntity> maloPrzedmiotow = new ArrayList<>();

    public MagazynQtyObserver(MagazynEntity magazyn, JFrame frame) {
        super(magazyn,frame);
    }

    @Override
    public void notifyObserver() {
        warnPopUp();
    }

    @Override
    public void warnPopUp() {
        for(PrzedmiotMagazynEntity przedmiotMagazyn: magazyn.getPrzedmiotyWMagazynie()) {
            if(przedmiotMagazyn.getIlosc()<5) maloPrzedmiotow.add(przedmiotMagazyn);
        }

        JDialog jDialog =new JDialog(frame,"Kończy się ilość produktów");
        JPanel panelContainer = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,2));
        panelContainer.add(panel,BorderLayout.CENTER);

        JLabel jLabel = new JLabel("Niski stan przedmiotów: ");

        JButton submit = new JButton("Uzupełnij");
        JButton cancel = new JButton("Wyjdź");

        panel.add(jLabel);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        for(PrzedmiotMagazynEntity przedmiotMagazyn: maloPrzedmiotow) {
            JLabel name = new JLabel(przedmiotMagazyn.getPrzedmiot().getNazwa());
            JLabel qty = new JLabel(przedmiotMagazyn.getIlosc().toString());

            panel.add(name);
            panel.add(qty);
        }
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(submit);
        panel.add(cancel);

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        jDialog.add(panelContainer);

        jDialog.setVisible(true);
        jDialog.requestFocus();
        jDialog.setSize(340,200);
        jDialog.setLocationRelativeTo(null);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(false);
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(false);
                updatePopUp();
            }
        });
    }

    @Override
    public void updatePopUp() {

        JDialog jDialog =new JDialog(frame,"Uzupełnij składy na magazynie");
        JPanel panelContainer = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,2));
        panelContainer.add(panel,BorderLayout.CENTER);

        JLabel jLabel = new JLabel("Niski stan przedmiotów: ");

        JButton submit = new JButton("Akceptuj");
        JButton cancel = new JButton("Wyjdź");

        panel.add(jLabel);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        List<JTextField> qtys = new ArrayList<>();

        for(PrzedmiotMagazynEntity przedmiotMagazyn: maloPrzedmiotow) {
            JLabel name = new JLabel(przedmiotMagazyn.getPrzedmiot().getNazwa());
            JTextField qty = new JTextField(przedmiotMagazyn.getIlosc().toString());

            qtys.add(qty);

            panel.add(name);
            panel.add(qty);
        }
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(submit);
        panel.add(cancel);

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        jDialog.add(panelContainer);

        jDialog.setVisible(true);
        jDialog.requestFocus();
        jDialog.setSize(340,200);
        jDialog.setLocationRelativeTo(null);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(false);
            }
        });

        submit.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(false);
                int idx = 0;
                for(PrzedmiotMagazynEntity przedmiotMagazyn: maloPrzedmiotow) {
                    przedmiotMagazyn.setIlosc(Long.parseLong(qtys.get(idx).getText()));
                    PrzedmiotMagazynEntityUpdate(przedmiotMagazyn);
                    idx++;
                }
            }
        });
    }

    @Override
    public void PrzedmiotMagazynEntityUpdate(PrzedmiotMagazynEntity przedmiotMagazyn) {
        PrzedmiotMagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().update(przedmiotMagazyn);
    }

}
