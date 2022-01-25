package org.example.services.States;

import org.example.jpa.entities.RezerwacjaEntity;

public class InProgressState extends ReservationState{
    public InProgressState(RezerwacjaEntity reservation) {
        super(reservation);
        System.out.println("REZERWACJA: W TRAKCIE");
    }

    @Override
    public String abort() {
        reservation.changeState(new AbortedState(reservation));

        return "Anulowanie rezerwacji";

    }

    @Override
    public String accept() {
        reservation.changeState(new AcceptedState(reservation));
        return "Akceptacja rezerwacji";
    }

    @Override
    public String pay() {
        return "Nie obsługuje";
    }

    @Override
    public String progress() {
        return "Następne siedzenie";
    }

}
