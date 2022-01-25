package org.example.services.States;

import org.example.jpa.entities.RezerwacjaEntity;

public class AcceptedState extends ReservationState{
    public AcceptedState(RezerwacjaEntity reservation) {
        super(reservation);
        System.out.println("REZERWACJA: AKCEPTACJA");
    }

    @Override
    public String abort() {
        reservation.changeState(new InProgressState(reservation));
        return "Wracamy do InProgress";
    }

    @Override
    public String accept() {
        return "Nie obsługuje";
    }

    @Override
    public String pay() {
        reservation.changeState(new PaidState(reservation));
        return "Płacenie";
    }

    @Override
    public String progress() {
        return "Nie obsługuje";
    }
}
