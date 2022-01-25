package org.example.services;

import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;

import javax.swing.*;

public abstract class MagazynObserver implements IMagazynObserver{
    MagazynEntity magazyn;

    public MagazynObserver(MagazynEntity magazyn) {
        this.magazyn = magazyn;
    }

    @Override
    public abstract JDialog WarnPopUp();

    @Override
    public abstract JDialog UpdatePopUp();

    @Override
    public abstract PrzedmiotMagazynEntity PrzedmiotMagazynEntityUpdate();
}
