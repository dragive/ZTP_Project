package org.example.jpa.controllers;

import org.example.jpa.entities.PrzedmiotEntity;

import org.example.jpa.repositories.PrzedmiotRepository;
import org.example.services.DatabaseService;

import org.example.ui.views.ErrorFrame;
import org.example.ui.views.ItemViews.AddPrzedmiotView;
import org.example.ui.views.ItemViews.EditPrzedmiotView;
import org.example.ui.views.ItemViews.PrzedmiotListView;
import org.example.ui.views.ItemViews.PrzedmiotView;
import org.example.ui.views.PopUps;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ItemController {
    private static ItemController instance;

    PrzedmiotRepository przedmiotRepository;

    //views
    PrzedmiotListView przedmiotListView;
    AddPrzedmiotView addPrzedmiotView;
    PrzedmiotView przedmiotView;
    EditPrzedmiotView editPrzedmiotView;

    //models
    PrzedmiotEntity przedmiotEntity;

    JFrame frame;

    private ItemController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        przedmiotRepository = PrzedmiotRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static ItemController getInstance(JFrame frame) {
        if(instance==null) instance = new ItemController(frame);
        return instance;
    }

    public List<PrzedmiotEntity> getAll() {
        return przedmiotRepository.getAll();
    }

    public PrzedmiotEntity getByName(String name) {
        return przedmiotRepository.getByName(name);
    }

    public void index() {
        List<PrzedmiotEntity> przedmiotEntityList = getAll();
        przedmiotListView = new PrzedmiotListView(przedmiotEntityList);
        frame.add(przedmiotListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        przedmiotListView.requestFocus();

        przedmiotListView.getAddPrzedmiot().addActionListener(new indexAddItemListener());
    }

    public void create() {
        addPrzedmiotView = new AddPrzedmiotView();
        frame.add(addPrzedmiotView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        przedmiotListView.requestFocus();

        addPrzedmiotView.getAccept().addActionListener(new createAcceptListener());
        addPrzedmiotView.getExit().addActionListener(new createExitListener());
    }

    public void details(PrzedmiotEntity przedmiotEntity) {
        przedmiotView = new PrzedmiotView(przedmiotEntity);
        frame.add(przedmiotView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        przedmiotView.getProductNameField().setText(przedmiotEntity.getNazwa());
        przedmiotView.getProductCategoryField().setText(przedmiotEntity.getKategoria());
        przedmiotView.getProductPriceBruttoField().setText(przedmiotEntity.getCena().toString());
        przedmiotView.getProductCzyNaWynosField().setText(przedmiotEntity.getCzyNaWynos()?"Tak":"Nie");
        przedmiotView.getProductProducentField().setText(przedmiotEntity.getProducent());
        przedmiotView.getProductDateField().setText(przedmiotEntity.getDataWaznosci().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        przedmiotView.getEditButton().addActionListener(new detailsEditListener(przedmiotEntity));
        przedmiotView.getExit().addActionListener(new detailsExitListener());
        przedmiotView.getDeleteButton().addActionListener(new detailsDeleteListener());
    }

    public void edit(PrzedmiotEntity przedmiotEntity){
        editPrzedmiotView = new EditPrzedmiotView(przedmiotEntity);
        frame.add(editPrzedmiotView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();


        editPrzedmiotView.getProductNameField().setText(przedmiotEntity.getNazwa());
        editPrzedmiotView.getProductCategoryField().setText(przedmiotEntity.getKategoria());
        editPrzedmiotView.getProductPriceBruttoField().setText(przedmiotEntity.getCena().toString());
        editPrzedmiotView.getProductCzyNaWynosField().setSelected(przedmiotEntity.getCzyNaWynos());
        editPrzedmiotView.getProductProducentField().setText(przedmiotEntity.getProducent());
        editPrzedmiotView.getProductDateField().setText(przedmiotEntity.getDataWaznosci().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));


        editPrzedmiotView.getAccept().addActionListener(new editAcceptListener(przedmiotEntity));
        editPrzedmiotView.getExit().addActionListener(new editExitListener());
    }

    //index

    private class indexAddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(przedmiotListView);
            create();
        }
    }

    //create

    private class createAcceptListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addPrzedmiotView);
            przedmiotEntity = new PrzedmiotEntity();
            try {
                przedmiotEntity.setId(przedmiotRepository.getNewId());

                przedmiotEntity.setNazwa(addPrzedmiotView.getProductNameField().getText());
                przedmiotEntity.setKategoria(addPrzedmiotView.getProductCategoryField().getText());
                przedmiotEntity.setCena(Double.parseDouble(addPrzedmiotView.getProductPriceBruttoField().getText()));
                przedmiotEntity.setCzyNaWynos(addPrzedmiotView.getProductCzyNaWynosField().isSelected());
                przedmiotEntity.setProducent(addPrzedmiotView.getProductProducentField().getText());
                przedmiotEntity.setDataWaznosci(LocalDateTime.parse(addPrzedmiotView.getProductDateField().getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) );

                validateItemEntity(przedmiotEntity);

                przedmiotRepository.save(przedmiotEntity);
            }
            catch (Exception ex){
                PopUps.Error("Wprowadzono błędne dane przedmiotu sprzedaży! Sprawdź czy formularz został wypełniony poprawnie!");
            }
            index();
        }
    }

    private class createExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addPrzedmiotView);
            index();
        }
    }

    //details

    private class detailsExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(przedmiotView);
            index();
        }
    }

    private class detailsDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(przedmiotView);

            przedmiotEntity = przedmiotView.getPrzedmiotEntity();
            try{
                if(PopUps.OkCancel("Czy na pewno chcesz usunąć ten przedmiot?"))
                    przedmiotRepository.delete(przedmiotEntity);
            }
            catch (Exception ex){
                PopUps.Error("Usuwanie się nie powiodło. Sprawdź czy przedmiot nie jest nigdzie używany!");
            }

            index();
        }
    }

    private class detailsEditListener implements ActionListener{
        PrzedmiotEntity kino;
        public detailsEditListener(PrzedmiotEntity kino) {
            this.kino = kino;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(przedmiotView);
            edit(this.kino);
        }
    }

    //edit

    private class editExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(editPrzedmiotView);
            index();
        }
    }
    private  class editAcceptListener implements ActionListener{
        PrzedmiotEntity przedmiotEntity1;
        public editAcceptListener(PrzedmiotEntity przedmiotEntity1){this.przedmiotEntity1 = przedmiotEntity1;}
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                PrzedmiotEntity przedmiotEntity1 = this.przedmiotEntity1;
                przedmiotEntity1.setNazwa(editPrzedmiotView.getProductNameField().getText());
                przedmiotEntity1.setKategoria(editPrzedmiotView.getProductCategoryField().getText());
                przedmiotEntity1.setCena(Double.parseDouble(editPrzedmiotView.getProductPriceBruttoField().getText()));

                przedmiotEntity1.setCzyNaWynos(editPrzedmiotView.getProductCzyNaWynosField().isSelected());
                przedmiotEntity1.setProducent(editPrzedmiotView.getProductProducentField().getText());
                przedmiotEntity1.setDataWaznosci(LocalDateTime.parse(editPrzedmiotView.getProductDateField().getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) );

                //validateItemEntity(przedmiotEntity1);

                przedmiotRepository.update(przedmiotEntity1);

                frame.remove(editPrzedmiotView);
                index();
            }catch (Exception ex) {
                System.out.println(ex);
                new ErrorFrame("Wprowadzono błędne dane przedmiotu sprzedaży! Sprawdź czy formularz został wypełniony poprawnie!");
            }
        }
    }

    public void validateItemEntity(PrzedmiotEntity itemEntity) throws Exception{
        if(itemEntity.getCena().equals("") || itemEntity.getCena()>0 || itemEntity.getDataWaznosci().equals("") || itemEntity.getKategoria().equals("") || itemEntity.getNazwa().equals("") || itemEntity.getProducent().equals("")){
            throw new Exception("Wystąpił błąd w formularzu!");
        }
    }
}
