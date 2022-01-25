package org.example.services;

import org.example.jpa.entities.PrzedmiotMagazynEntity;

import javax.swing.*;

public interface IMagazynObserver {
    void notifyObserver();
    void warnPopUp();
    void updatePopUp();
    void PrzedmiotMagazynEntityUpdate(PrzedmiotMagazynEntity przedmiotMagazyn);
}
