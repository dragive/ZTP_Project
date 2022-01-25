package org.example.services.States;

import org.example.jpa.entities.RezerwacjaEntity;

public abstract class ReservationState {
    RezerwacjaEntity reservation;

    ReservationState(RezerwacjaEntity reservation) {
        this.reservation = reservation;
    }

    public abstract String abort();
    public abstract String accept();
    public abstract String pay();
    public abstract String progress();
}

