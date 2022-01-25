package org.example.services.States;

import org.example.jpa.entities.RezerwacjaEntity;

public class PaidState extends ReservationState{
    public PaidState(RezerwacjaEntity reservation) {
        super(reservation);
        System.out.println("REZERWACJA: OPLACONA");
    }

    @Override
    public String abort() {
        reservation.setCzyOplacona(false);
        reservation.changeState(new InProgressState(reservation));
        return "Cofanie transakcji";
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
        return "Nie obsługuje";
    }
}
