package org.example.jpa.controllers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.*;
import org.example.jpa.entities.PrzedmiotEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;
import org.example.jpa.repositories.MagazynRepository;
import org.example.jpa.repositories.PrzedmiotMagazynRepository;
import org.example.services.DatabaseService;
import org.example.services.IMagazynObserver;
import org.example.services.MagazynQtyObserver;
import org.example.ui.views.MagazynViews.*;
import org.example.ui.views.PopUps;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MagazynController {
    private static MagazynController instance;

    MagazynRepository magazynRepository;

    //views
    MagazynListView magazynListView;
    MagazynView magazynView;
    AddMagazynView addMagazynView;
    EditMagazynView editMagazynView;
    ManageItemsListMagazynView manageItemsListMagazynView;
    AddItemMagazynView addItemMagazynView;
    MagazynItemsView magazynitems;

    //models
    MagazynEntity magazynEntity;

    JFrame frame;

    private MagazynController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        magazynRepository = MagazynRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static MagazynController getInstance(JFrame frame) {
        if(instance==null) instance = new MagazynController(frame);
        return instance;
    }

    public void index() {
        List<MagazynEntity> magazynEntityList = magazynRepository.getAll();

        IMagazynObserver magazynQtyObserver;
        for(MagazynEntity magazyn: magazynEntityList) {
            if(magazyn.getObservers().size()==0) {
                magazynQtyObserver = new MagazynQtyObserver(magazyn,frame);
                magazyn.attach(magazynQtyObserver);
            }
        }

        magazynListView = new MagazynListView(magazynEntityList);
        frame.add(magazynListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        magazynListView.requestFocus();

        magazynListView.getAddMagazyn().addActionListener(new indexAddMagazynListener());
    }

    public void details(MagazynEntity magazyn) {
        magazynView = new MagazynView(magazyn);
        frame.add(magazynView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        magazynView.requestFocus();
        System.out.println(magazyn.getPrzedmiotyWMagazynie());
        if(magazyn.getPrzedmiotyWMagazynie().size()==0) magazynView.getBuyProduct().setEnabled(false);
        magazynView.getExit().addActionListener(new detailsExitListener());
        magazynView.getDelete().addActionListener(new detailsDeleteListener(magazyn));
        magazynView.getEdit().addActionListener(new detailsEditListener(magazyn));
        magazynView.getEditItemsInStorage().addActionListener(new editItemsInStorageAL(magazyn));
        magazynView.getBuyProduct().addActionListener(new detailsBuyProductListener(magazyn));
        magazynView.getProductTransactions().addActionListener(new detailsProductTransactionListener(magazyn));

        List<PrzedmiotMagazynEntity> maloPrzedmiotow = new ArrayList<>();

        for(PrzedmiotMagazynEntity przedmiotMagazyn: magazyn.getPrzedmiotyWMagazynie()) {
            if(przedmiotMagazyn.getIlosc()<5) maloPrzedmiotow.add(przedmiotMagazyn);
        }

        if(maloPrzedmiotow.size()>0) {
            magazyn.notifyObservers();
        }
    }

    public void create() {
        addMagazynView = new AddMagazynView();
        frame.add(addMagazynView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addMagazynView.requestFocus();

        addMagazynView.getAccept().setEnabled(false);
        addMagazynView.getExit().addActionListener(new createExitListener());


    }

    public void createRepaint(KinoEntity kino,AddMagazynView addMagazynViewOld) {

        this.addMagazynView = new AddMagazynView();

        addMagazynView.getAddress().setText(addMagazynViewOld.getAddress().getText());
        addMagazynView.getCity().setText(addMagazynViewOld.getCity().getText());
        addMagazynView.setKino(kino);
        addMagazynView.getOpis().setText(addMagazynViewOld.getOpis().getText());


        frame.remove(addMagazynViewOld);
        frame.add(addMagazynView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addMagazynView.requestFocus();

        addMagazynView.getAccept().addActionListener(new createAcceptListener(kino));
        addMagazynView.getExit().addActionListener(new createExitListener());

        addMagazynView.getNameOfCinema().setText(kino.getName());
        addMagazynView.setKino(kino);

    }
    
    public void edit(MagazynEntity entity){
        editMagazynView = new EditMagazynView();
        frame.add(editMagazynView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        editMagazynView.requestFocus();
        editMagazynView.getAccept().addActionListener(new editAcceptListener(entity));
        editMagazynView.getExit().addActionListener(new editExitListener(entity));


        editMagazynView.getAddress().setText(entity.getAdres());
        editMagazynView.getCinema().setText(entity.getKino().getName()); // if not works try:
//        KinoRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById(entity.getId()).getName();
        editMagazynView.getCity().setText(entity.getMiasto());
        editMagazynView.getOpis().setText(entity.getOpis());


    }

    public void manageItemsOfStorage(MagazynEntity magazyn){
        MagazynEntity magazynEntity= magazyn;

        manageItemsListMagazynView = new ManageItemsListMagazynView(magazynEntity);
        frame.add(manageItemsListMagazynView,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        manageItemsListMagazynView.requestFocus();

        manageItemsListMagazynView.getExit().addActionListener(new manageItemsListExit(magazynEntity));
        manageItemsListMagazynView.getAddNewItem().addActionListener(new manageItemsListAddNewItem(magazynEntity));
    }

    public void manageItemsOfStorageRepaint(MagazynEntity magazyn){
        MagazynEntity magazynEntity= magazyn;
        magazynEntity = MagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById(magazynEntity.getId());
        frame.remove(manageItemsListMagazynView);
        manageItemsListMagazynView = new ManageItemsListMagazynView(magazynEntity);
        frame.add(manageItemsListMagazynView,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        manageItemsListMagazynView.requestFocus();

        manageItemsListMagazynView.getExit().addActionListener(new manageItemsListExit(magazynEntity));
        manageItemsListMagazynView.getAddNewItem().addActionListener(new manageItemsListAddNewItem(magazynEntity));
    }

    public void addNewItemRepaint(MagazynEntity magazyn,String s ){
        MagazynEntity magazynEntity= magazyn;
        magazynEntity = MagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById(magazynEntity.getId());
        PrzedmiotEntity przedmiot = addItemMagazynView.getPrzedmiotEntity();

        frame.remove(addItemMagazynView);
        addItemMagazynView = new AddItemMagazynView(magazynEntity);
        if(s!=null)
            addItemMagazynView.getAmountTextField().setText(s);
        log.info(s);
        frame.add(addItemMagazynView,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addItemMagazynView.requestFocus();




        addItemMagazynView.getExit().addActionListener(new addNewItemsExit(magazyn));
        addItemMagazynView.getChosenItem().setText(przedmiot.getNazwa());
        addItemMagazynView.getAccept().addActionListener(new addNewItemsAccept(magazyn,przedmiot));
    }

    public void addNewItem(MagazynEntity magazyn){

        frame.remove(magazynListView);
        addItemMagazynView = new AddItemMagazynView(magazyn);
        frame.add(addItemMagazynView,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addItemMagazynView.requestFocus();

        addItemMagazynView.getExit().addActionListener(new addNewItemsExit(magazyn));

        addItemMagazynView.getAccept().setEnabled(false);
    }

    //index

    private class indexAddMagazynListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(magazynListView);
            create();
        }
    }

    //details

    private class detailsExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(magazynView);
            index();
        }
    }

    //create

    private class createAcceptListener implements ActionListener {
        KinoEntity kino;
        createAcceptListener(KinoEntity kino){
            this.kino = kino;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MagazynEntity newMagazynEntity = new MagazynEntity();
                newMagazynEntity.setAdres(addMagazynView.getAddress().getText());
                newMagazynEntity.setOpis(addMagazynView.getOpis().getText());
                newMagazynEntity.setMiasto(addMagazynView.getCity().getText());
                newMagazynEntity.setKino(kino);
                //Stara implementacja
                // newMagazynEntity.setKino(CinemaController.getInstance(frame).getByName(addMagazynView.getCinemaSelect().getText()));
                newMagazynEntity.setPrzedmiotyWMagazynie(null);
                newMagazynEntity.setId(magazynRepository.getNewId());

                validateMagazynEntity(newMagazynEntity);

                magazynRepository.save(newMagazynEntity);
                log.error(newMagazynEntity.toString());
                frame.remove(addMagazynView);
                index();
            } catch (Exception ex) {
            
                log.error(ex.toString());

                PopUps.Error("Błędne dane dotyczące magazynu! Sprawdź czy formularz jest wypełniony poprawnie!");
                
            }
        }
    }

    private class createExitListener implements ActionListener {
        @SneakyThrows
        @Override

        public void actionPerformed(ActionEvent e) {
            frame.remove(addMagazynView);
            index();
        }
    }

    //delete

    private class detailsDeleteListener implements ActionListener {
        MagazynEntity magazynEntity;
        public  detailsDeleteListener(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {


            try{
                if(PopUps.OkCancel("Czy na pewno chcesz usunąć to kino?"))
                    magazynRepository.delete(magazynEntity);
                frame.remove(magazynView);

                index();
            }
            catch (Exception ex){
                PopUps.Error("Usuwanie się nie powiodło. Sprawdź czy magazyn nie posiada w sobie przedmiorów!");
            }


        }
    }

    //edit

    private class detailsEditListener implements ActionListener {
        MagazynEntity magazynEntity;
        public detailsEditListener(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(magazynView);

            edit(magazynEntity);


        }
    }

    private class editItemsInStorageAL implements ActionListener{
        MagazynEntity magazynEntity;
        public editItemsInStorageAL(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(magazynView);
            manageItemsOfStorage(magazynEntity);
        }

    }


    private class editAcceptListener implements ActionListener {
        MagazynEntity magazynEntity;
        public editAcceptListener(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MagazynEntity newMagazynEntity = this.magazynEntity;
                newMagazynEntity.setAdres(editMagazynView.getAddress().getText());
                newMagazynEntity.setOpis(editMagazynView.getOpis().getText());
                newMagazynEntity.setMiasto(editMagazynView.getCity().getText());
//                newMagazynEntity.setKino(CinemaController.getInstance(frame).getByName(editMagazynView.getCinema().getText()));
                newMagazynEntity.setPrzedmiotyWMagazynie(null);
//                newMagazynEntity.setId(magazynRepository.getNewId());
//                newMagazynEntity.setKino(null);

                validateMagazynEntity(newMagazynEntity);

                magazynRepository.update(newMagazynEntity);

                frame.remove(editMagazynView);
                index();
            } catch (Exception ex) {
                log.error(""+ex);
                PopUps.Error("Błędne dane dotyczące magazynu! Sprawdź czy formularz jest wypełniony poprawnie!");
            }
        }
    }

    private class editExitListener implements ActionListener {

        MagazynEntity magazynEntity;
        public editExitListener(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(editMagazynView);
            details(magazynEntity);
        }
    }

    //manage items

    private class manageItemsListExit implements ActionListener{
        MagazynEntity magazynEntity;
        public manageItemsListExit(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(manageItemsListMagazynView);
            details(magazynEntity);
        }
    }

    public void validateMagazynEntity(MagazynEntity magazynEntity) throws Exception{
        if(magazynEntity.getAdres().equals("") || magazynEntity.getMiasto().equals("") || magazynEntity.getOpis().equals("") || magazynEntity.getKino().equals("")){
            throw new Exception("Błędne dane dotyczące magazynu!");

        }
    }

    private class manageItemsListAddNewItem implements ActionListener{
        MagazynEntity magazynEntity;
        public manageItemsListAddNewItem(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(manageItemsListMagazynView);
            addNewItem(magazynEntity);
        }
    }

    private class addNewItemsExit implements ActionListener{
        MagazynEntity magazynEntity;
        public addNewItemsExit(MagazynEntity magazynEntity){
            this.magazynEntity = magazynEntity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addItemMagazynView);
            manageItemsOfStorage(magazynEntity);
        }
    }

    private class addNewItemsAccept implements ActionListener{
        MagazynEntity magazynEntity;
        PrzedmiotEntity przedmiot;
        Long amount;
        public addNewItemsAccept(MagazynEntity magazyn,PrzedmiotEntity przedmiot){
            this.magazynEntity = magazyn;
            this.przedmiot = przedmiot;

        }

        @Override
        @SneakyThrows
        public void actionPerformed(ActionEvent e) {

            if(przedmiot == null){
                throw new Exception("Puste!");
            }
            PrzedmiotMagazynRepository przedmiotMagazynRepository =PrzedmiotMagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build();
            PrzedmiotMagazynEntity przedmiotMagazynEntity = new PrzedmiotMagazynEntity();

            przedmiotMagazynEntity.getId().setMagazynId(magazynEntity.getId());
            przedmiotMagazynEntity.getId().setPrzedmiotId(przedmiot.getId());
            przedmiotMagazynEntity.setMagazyn(magazynEntity);
            przedmiotMagazynEntity.setPrzedmiot(przedmiot);



            try {
//
                amount = Long.parseLong((addItemMagazynView.getAmountTextField() == null ? new JTextField("0") : addItemMagazynView.getAmountTextField()).getText());
                if(amount<0){
                    throw new Exception("Wprowadź poprawną wartość!");
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
                amount = 0L;
                PopUps.Error("Wprowadzono niepoprawną wartość stanu magazynu!");
            }


            przedmiotMagazynEntity.setIlosc(amount);

            przedmiotMagazynRepository.save(przedmiotMagazynEntity);

            frame.remove(addItemMagazynView);
            manageItemsOfStorage(magazynEntity);

        }
    }

    private class detailsBuyProductListener implements ActionListener {
        private MagazynEntity magazyn;

        public detailsBuyProductListener(MagazynEntity magazyn) {
            this.magazyn = magazyn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<PrzedmiotMagazynEntity> products = new ArrayList<>();
            for(PrzedmiotMagazynEntity item: magazyn.getPrzedmiotyWMagazynie()) {
                PrzedmiotMagazynEntity newItem = new PrzedmiotMagazynEntity();
                newItem.setMagazyn(item.getMagazyn());
                newItem.setId(item.getId());
                newItem.setIlosc(item.getIlosc());
                newItem.setPrzedmiot(item.getPrzedmiot());
                products.add(newItem);
            }
            List<PrzedmiotMagazynEntity> transactionProducts = new ArrayList<>();
            frame.remove(magazynView);
            ProductTransactionController.getInstance(frame).productList(transactionProducts,products);
        }
    }

    private class detailsProductTransactionListener implements ActionListener {
        private MagazynEntity magazyn;

        public detailsProductTransactionListener(MagazynEntity magazyn) {
            this.magazyn = magazyn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(magazynView);
            ProductTransactionController productTransactionController = ProductTransactionController.getInstance(frame);
            productTransactionController.transactionList(magazyn);
        }
    }
}
