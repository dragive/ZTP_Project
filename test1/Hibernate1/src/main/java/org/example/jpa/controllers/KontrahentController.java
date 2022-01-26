package org.example.jpa.controllers;

import org.example.jpa.entities.KontrahentEntity;
import org.example.jpa.repositories.KontrahentRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.ErrorFrame;
import org.example.ui.views.KontrahentViews.AddKontrahentView;
import org.example.ui.views.KontrahentViews.EditKontrahentView;
import org.example.ui.views.KontrahentViews.KontrahentListView;
import org.example.ui.views.KontrahentViews.KontrahentView;
import org.example.ui.views.PopUps;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KontrahentController {
    private static KontrahentController instance;

    KontrahentRepository kontrahentRepository;

    //views
    KontrahentListView kontrahentListView;
    KontrahentView kontrahentView;
    AddKontrahentView addKontrahentView;
    EditKontrahentView editKontrahentView;

    //models
    KontrahentEntity kontrahentEntity;

    JFrame frame;

    private KontrahentController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        kontrahentRepository = KontrahentRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static KontrahentController getInstance(JFrame frame) {
        if(instance==null) instance = new KontrahentController(frame);
        return instance;
    }

    public void index() {
        List<KontrahentEntity> kontrahentEntityList = kontrahentRepository.getAll();
        kontrahentListView = new KontrahentListView(kontrahentEntityList);
        frame.add(kontrahentListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        kontrahentListView.requestFocus();

        kontrahentListView.getAddKontrahent().addActionListener(new indexAddMagazynListener());
    }

    public void details(KontrahentEntity kontrahent) {
        kontrahentView = new KontrahentView(kontrahent);
        frame.add(kontrahentView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        kontrahentView.requestFocus();

        kontrahentView.getExit().addActionListener(new detailsExitListener());
        System.out.println(kontrahent);
        kontrahentView.getEditButton().addActionListener(new editEnterListener(kontrahent));
        kontrahentView.getDeleteButton().addActionListener(new deleteEnterListener(kontrahent));
    }

    public void edit(KontrahentEntity kontrahent){
        editKontrahentView = new EditKontrahentView(kontrahent);
        frame.add(editKontrahentView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        editKontrahentView.requestFocus();

        editKontrahentView.getAccept().addActionListener(new editAcceptListener(kontrahent));
        editKontrahentView.getExit().addActionListener(new editExitListener(kontrahent));

        editKontrahentView.getCompanyName().setText(kontrahent.getNazwaFirmy());
        editKontrahentView.getNIP().setText(kontrahent.getNip());
        editKontrahentView.getAddress().setText(kontrahent.getAdres());
        editKontrahentView.getCity().setText(kontrahent.getMiasto());




    }

    public void create() {
        addKontrahentView = new AddKontrahentView();
        frame.add(addKontrahentView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addKontrahentView.requestFocus();

        addKontrahentView.getAccept().addActionListener(new createAcceptListener());
        addKontrahentView.getExit().addActionListener(new createExitListener());
    }

    //index

    private class indexAddMagazynListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(kontrahentListView);
            create();
        }
    }

    //details

    private class detailsExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(kontrahentView);
            index();
        }
    }


    private class editEnterListener implements ActionListener {
        private KontrahentEntity kontrahent;

        protected editEnterListener(KontrahentEntity kontrahent){
            this.kontrahent=kontrahent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(kontrahentView);
            System.out.println(kontrahent);
            edit(kontrahent);
        }
    }

    //create

    private class createAcceptListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                KontrahentEntity newKontrahentEntity = new KontrahentEntity();
                newKontrahentEntity.setAdres(addKontrahentView.getAddress().getText());
                newKontrahentEntity.setNazwaFirmy(addKontrahentView.getCompanyName().getText());
                newKontrahentEntity.setMiasto(addKontrahentView.getCity().getText());
                newKontrahentEntity.setNip(addKontrahentView.getNIP().getText());
                newKontrahentEntity.setId(kontrahentRepository.getNewId());

                validateKontrahentEntity(newKontrahentEntity);

                kontrahentRepository.save(newKontrahentEntity);

                frame.remove(addKontrahentView);
                index();
            } catch (Exception ex) {
                new ErrorFrame("Błędne dane. Sprawdź czy wszystko wpisałeś dobrze");
            }
        }
    }

    private class createExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addKontrahentView);
            index();
        }
    }

    //edit

    private class editAcceptListener implements ActionListener{
        private KontrahentEntity kontrahent;
        protected editAcceptListener(KontrahentEntity kontrahent){
                this.kontrahent = kontrahent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                KontrahentEntity newKontrahentEntity = this.kontrahent;
                newKontrahentEntity.setAdres(editKontrahentView.getAddress().getText());
                newKontrahentEntity.setNazwaFirmy(editKontrahentView.getCompanyName().getText());
                newKontrahentEntity.setMiasto(editKontrahentView.getCity().getText());
                newKontrahentEntity.setNip(editKontrahentView.getNIP().getText());
                // niepotrzebna deklaracja bo jest edycja tylko
                //                newKontrahentEntity.setId(kontrahentRepository.getNewId());

                //validateKontrahentEntity(newKontrahentEntity);

                kontrahentRepository.update(newKontrahentEntity);

                frame.remove(editKontrahentView);
                index();
            }catch (Exception ex) {
                System.out.println(ex);
                new ErrorFrame("Błędne dane. Sprawdź czy wszystko wpisałeś dobrze");
            }
        }
    }

    private class editExitListener implements ActionListener{
        KontrahentEntity kontrahent;
        editExitListener(KontrahentEntity kontrahent){
            this.kontrahent = kontrahent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(editKontrahentView);
            details(kontrahent);
        }
    };


    //delete

    private class deleteEnterListener implements ActionListener{
        private KontrahentEntity kontrahent;
        protected deleteEnterListener(KontrahentEntity kontrahent){
            this.kontrahent = kontrahent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(kontrahentView);
            try {
                boolean ok = PopUps.OkCancel("Czy na pewno chcesz usunąć dane kontrahenta?");
                if(ok){
                    kontrahentRepository.delete(this.kontrahent);
                }
            }
            catch (Exception ex){
                PopUps.Error("Wystąpił błąd podczas usuwania!");
            }
                        index();
        }
    }

    public void validateKontrahentEntity(KontrahentEntity kontrahentEntity) throws Exception{
        if(kontrahentEntity.getAdres().equals("") || kontrahentEntity.getMiasto().equals("") || kontrahentEntity.getNip().equals("") || kontrahentEntity.getNip().length()!=10 || kontrahentEntity.getNazwaFirmy().equals("")){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }

}
