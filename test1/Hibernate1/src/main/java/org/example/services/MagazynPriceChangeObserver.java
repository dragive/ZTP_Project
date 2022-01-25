package org.example.services;

import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;

import javax.swing.*;

public class MagazynPriceChangeObserver extends MagazynObserver {
    public MagazynPriceChangeObserver(MagazynEntity magazyn) {
        super(magazyn);
    }

    @Override
    public JDialog WarnPopUp() {
        return null;
    }

    @Override
    public JDialog UpdatePopUp() {
        return null;
    }

    @Override
    public PrzedmiotMagazynEntity PrzedmiotMagazynEntityUpdate() {
        return null;
    }
}
