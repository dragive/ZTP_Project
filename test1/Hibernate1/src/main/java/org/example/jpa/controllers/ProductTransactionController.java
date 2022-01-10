package org.example.jpa.controllers;

import org.example.jpa.entities.*;
import org.example.jpa.repositories.MagazynRepository;
import org.example.jpa.repositories.ProductTransactionRepository;
import org.example.jpa.repositories.SalaRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.MagazynViews.MagazynItemsView;
import org.example.ui.views.MenuPanel;
import org.example.ui.views.TransactionViews.AddProductTransactionView;
import org.example.ui.views.TransactionViews.ProductListTransactionView;
import org.example.ui.views.TransactionViews.TransactionListView;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductTransactionController {
    private static ProductTransactionController instance;

    ProductTransactionRepository productTransactionRepository;

    AddProductTransactionView addProductTransactionView;
    ProductListTransactionView productListTransactionView;
    MagazynItemsView magazynitems;
    TransactionListView transactionListView;

    JFrame frame;

    private ProductTransactionController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        productTransactionRepository = ProductTransactionRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static ProductTransactionController getInstance(JFrame frame) {
        if(instance==null) instance = new ProductTransactionController(frame);
        return instance;
    }

    public void index(List<PrzedmiotMagazynEntity> transactionProducts, List<PrzedmiotMagazynEntity> magazynProducts) {
        productListTransactionView = new ProductListTransactionView(transactionProducts);
        frame.add(productListTransactionView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        productListTransactionView.requestFocus();

        if(transactionProducts.size()==0) productListTransactionView.getAccept().setEnabled(false);
        productListTransactionView.getAddProduct().addActionListener(new indexAddProductListener(transactionProducts,magazynProducts));
        productListTransactionView.getAccept().addActionListener(new indexAcceptListener(transactionProducts,magazynProducts));
        productListTransactionView.getExit().addActionListener(new indexExitListener(magazynProducts.get(magazynProducts.size()-1)));
    }

    public void create(List<PrzedmiotMagazynEntity> transactionProducts, List<PrzedmiotMagazynEntity> magazynProducts) {
        addProductTransactionView = new AddProductTransactionView();
        frame.add(addProductTransactionView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        addProductTransactionView.requestFocus();

        addProductTransactionView.getAccept().addActionListener(new createAcceptListener(transactionProducts,magazynProducts));
        PrzedmiotMagazynEntity przedmiotMagazynEntity = magazynProducts.get(magazynProducts.size()-1);
        addProductTransactionView.getExit().addActionListener(new createExitListener(przedmiotMagazynEntity));
    }

    public void productList(List<PrzedmiotMagazynEntity> transactionProducts, List<PrzedmiotMagazynEntity> magazynProducts) {
        List<JButton> buttonList = new ArrayList<>();

        for(PrzedmiotMagazynEntity product: magazynProducts) {
            JButton temp = new JButton(product.getPrzedmiot().getNazwa());
            if(product.getIlosc()<=0) temp.setEnabled(false);
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.remove(magazynitems);


                    product.setIlosc(product.getIlosc()-1);

                    boolean flag = false;

                    for(PrzedmiotMagazynEntity temp: transactionProducts) {
                        if(temp.getId().equals(product.getId())) {
                            flag=true;
                            temp.setIlosc(temp.getIlosc()+1);
                            break;
                        }
                    }
                    if(flag!=true) {
                        PrzedmiotMagazynEntity modifiedProduct = new PrzedmiotMagazynEntity();
                        modifiedProduct.setIlosc(1L);
                        modifiedProduct.setPrzedmiot(product.getPrzedmiot());
                        modifiedProduct.setMagazyn(product.getMagazyn());
                        modifiedProduct.setId(product.getId());
                        transactionProducts.add(modifiedProduct);
                    }
                    index(transactionProducts,magazynProducts);
                }
            });
            buttonList.add(temp);
        }

        magazynitems = new MagazynItemsView(buttonList);
        frame.add(magazynitems, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        magazynitems.requestFocus();

        magazynitems.getExit().addActionListener(new productListExitListener(transactionProducts,magazynProducts));
    }

    public void transactionList(MagazynEntity magazyn) {

        List<PrzedmiotTransakcjaEntity> przedmiotTransakcjaEntity = MagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().getById(magazyn.getId()).getTransakcje();
        transactionListView = new TransactionListView(przedmiotTransakcjaEntity);
        frame.add(transactionListView, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        transactionListView.requestFocus();

        transactionListView.getExit().addActionListener(new transactionListExitListener(magazyn));
    }


    //create

    private class createExitListener implements ActionListener {
        private PrzedmiotMagazynEntity przedmiot;

        public createExitListener(PrzedmiotMagazynEntity przedmiot) {
            this.przedmiot = przedmiot;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(addProductTransactionView);
            MagazynController.getInstance(frame).details(przedmiot.getMagazyn());
        }
    }

    private class indexAcceptListener implements ActionListener {

        private List<PrzedmiotMagazynEntity> transactionProducts;
        private List<PrzedmiotMagazynEntity> magazynProducts;

        public indexAcceptListener(List<PrzedmiotMagazynEntity> transactionProducts, List<PrzedmiotMagazynEntity> magazynProducts) {

            this.transactionProducts = transactionProducts;
            this.magazynProducts = magazynProducts;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(productListTransactionView);
            create(transactionProducts,magazynProducts);
        }
    }

    private class indexAddProductListener implements ActionListener {

        private List<PrzedmiotMagazynEntity> transactionProducts;
        private List<PrzedmiotMagazynEntity> magazynProducts;

        public indexAddProductListener(List<PrzedmiotMagazynEntity> transactionProducts, List<PrzedmiotMagazynEntity> magazynProducts) {
            this.transactionProducts = transactionProducts;

            this.magazynProducts = magazynProducts;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(productListTransactionView);
            productList(transactionProducts, magazynProducts);
        }
    }

    private class productListExitListener implements ActionListener {
        private List<PrzedmiotMagazynEntity> transactionProducts;
        private List<PrzedmiotMagazynEntity> magazynProducts;

        public productListExitListener(List<PrzedmiotMagazynEntity> transactionProducts, List<PrzedmiotMagazynEntity> magazynProducts) {
            this.transactionProducts = transactionProducts;
            this.magazynProducts = magazynProducts;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("123");
            frame.remove(magazynitems);
            index(transactionProducts,magazynProducts);
        }
    }

    private class indexExitListener implements ActionListener {
        private PrzedmiotMagazynEntity przedmiotMagazynEntity;

        public indexExitListener(PrzedmiotMagazynEntity przedmiotMagazynEntity) {
            this.przedmiotMagazynEntity = przedmiotMagazynEntity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(productListTransactionView);
            MagazynController.getInstance(frame).details(przedmiotMagazynEntity.getMagazyn());
        }
    }

    private class createAcceptListener implements ActionListener {
        private List<PrzedmiotMagazynEntity> transactionProducts;
        private List<PrzedmiotMagazynEntity> magazynProducts;

        public createAcceptListener(List<PrzedmiotMagazynEntity> transactionProducts, List<PrzedmiotMagazynEntity> magazynProducts) {
            this.transactionProducts = transactionProducts;
            this.magazynProducts = magazynProducts;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            PrzedmiotTransakcjaEntity productTransaction = new PrzedmiotTransakcjaEntity();
            productTransaction.setCena(Double.parseDouble(addProductTransactionView.getPrice().getText()));
            productTransaction.setId(productTransactionRepository.getNewId());
            productTransaction.setCzyFaktura(addProductTransactionView.getFaktura().isSelected());
            productTransaction.setPracownik((PracownikEntity) MenuPanel.user);
            if(addProductTransactionView.getGotowka().isSelected()) {
                productTransaction.setCzyGotowka(true);
                productTransaction.setCzyKarta(false);
            }
            else {
                productTransaction.setCzyGotowka(false);
                productTransaction.setCzyKarta(true);
            }

            List<KontrahentEntity> kontrahentEntityList = KontrahentController.getInstance(frame).kontrahentRepository.getAll();
            KontrahentEntity kontrahent = null;

            for(KontrahentEntity kontrahentEntity: kontrahentEntityList) {
                if(kontrahentEntity.getNip().equals(addProductTransactionView.getKontrahent().getText())) {
                    kontrahent = kontrahentEntity;
                    break;
                }
            }

            productTransaction.setKontrahent(kontrahent);
            List<PrzedmiotEntity> products = new ArrayList<>();
            for(PrzedmiotMagazynEntity product: transactionProducts) {
                products.add(product.getPrzedmiot());
            }

            productTransaction.setPrzedmiotyTransakcji(products);

            MagazynEntity magazyn = magazynProducts.get(magazynProducts.size()-1).getMagazyn();

            productTransaction.setMagazyn(magazyn);

            productTransactionRepository.save(productTransaction);

            MagazynController magazynController = MagazynController.getInstance(frame);

            //MagazynEntity magazyn = magazynController.magazynRepository.getById(magazynProducts.get(magazynProducts.size()-1).getId().getMagazynId());

            //MagazynEntity magazyn = magazynProducts.get(magazynProducts.size()-1).getMagazyn();

            List<PrzedmiotMagazynEntity> przedmiotyWMagazynie = magazyn.getPrzedmiotyWMagazynie();
            for(PrzedmiotMagazynEntity przedmiotMagazyn: przedmiotyWMagazynie) {
                for(PrzedmiotMagazynEntity temp:magazynProducts) {
                    if(przedmiotMagazyn.getId().equals(temp.getId())) {
                        przedmiotMagazyn.setMagazyn(temp.getMagazyn());
                        przedmiotMagazyn.setPrzedmiot(temp.getPrzedmiot());
                        przedmiotMagazyn.setIlosc(temp.getIlosc());
                        przedmiotMagazyn.setId(temp.getId());
                    }
                }
            }
            magazyn.setPrzedmiotyWMagazynie(przedmiotyWMagazynie);
            MagazynRepository.builder().sessionFactory(DatabaseService.getInstance().getSessionFactory()).build().update(magazyn);

            frame.remove(addProductTransactionView);
            magazynController.details(magazyn);
        }
    }

    private class transactionListExitListener implements ActionListener {
        private MagazynEntity magazyn;

        public transactionListExitListener(MagazynEntity magazyn) {
            this.magazyn = magazyn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.remove(transactionListView);
            MagazynController magazynController = MagazynController.getInstance(frame);
            magazynController.details(magazyn);
        }
    }
}
