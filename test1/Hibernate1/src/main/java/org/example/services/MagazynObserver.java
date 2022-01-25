package org.example.services;

import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;

import javax.swing.*;

public abstract class MagazynObserver implements IMagazynObserver{
    MagazynEntity magazyn;
    JFrame frame;

    public MagazynObserver(MagazynEntity magazyn, JFrame frame) {
        this.magazyn = magazyn;
        this.frame = frame;
    }

    @Override
    public abstract void notifyObserver();

    @Override
    public abstract void warnPopUp();

    @Override
    public abstract void updatePopUp();

    @Override
    public abstract void PrzedmiotMagazynEntityUpdate(PrzedmiotMagazynEntity przedmiotMagazyn);
}
