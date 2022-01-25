package org.example.jpa.entities;

import org.example.services.IMagazynObserver;
import org.example.services.MagazynObserver;

import java.util.List;

public interface IMagazynEntity {
    void attach(MagazynObserver observer);
    void detach(MagazynObserver observer);
    void notifyObservers();
    List<IMagazynObserver> getObservers();
}
