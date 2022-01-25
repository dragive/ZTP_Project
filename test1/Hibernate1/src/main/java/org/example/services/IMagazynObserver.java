package org.example.services;

import org.example.jpa.entities.PrzedmiotMagazynEntity;

import javax.swing.*;

public interface IMagazynObserver {
    JDialog WarnPopUp();
    JDialog UpdatePopUp();
    PrzedmiotMagazynEntity PrzedmiotMagazynEntityUpdate();
}
