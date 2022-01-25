package org.example.services.States;

import org.example.jpa.entities.RezerwacjaEntity;

public class AbortedState extends ReservationState{
    public AbortedState(RezerwacjaEntity reservation) {
        super(reservation);
        System.out.println("REZERWACJA: WYCOFANIE");
    }

    @Override
    public String abort() {
        reservation = null;
        return "Usuwanie rezerwacji";
    }

    @Override
    public String accept() {
        return "Nie obsługuje";
    }

    @Override
    public String pay() {
        return "Nie obsługuje";
    }

    @Override
    public String progress() {
        reservation.changeState(new InProgressState(reservation));
        return "Anulowanie usuwania";
    }
}
