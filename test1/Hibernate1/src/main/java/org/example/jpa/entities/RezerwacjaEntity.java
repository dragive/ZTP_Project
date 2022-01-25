package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.services.States.InProgressState;
import org.example.services.States.ReservationState;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "REZERWACJA")
@Entity
@Getter
@Setter
@ToString
public class RezerwacjaEntity {
    @Transient
    ReservationState reservationState;

    public RezerwacjaEntity() {
        changeState(new InProgressState(this));
    }


    @Id
    @Column(name = "REZERWACJA_ID", nullable = false)
    private Long id;

    @Column(name = "NAZWA", nullable = false, length = 100)
    private String nazwa;

    @Column(name = "DATA_REZERWACJI", nullable = false)
    private LocalDate dataRezerwacji;

    @Column(name = "CZY_OPLACONA", nullable = false)
    private Boolean czyOplacona = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SEANS_ID", nullable = false)
    private SeansEntity seans;

    @ManyToOne
    @JoinColumn(name = "KLIENT_ID")
    private KlientEntity klient;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="REZERWACJA_ID")
    @ToString.Exclude
    private List<RezerwacjaTransakcjaEntity> transakcje = new ArrayList<>();

    @ManyToMany(mappedBy = "fotelReservations",cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<FotelEntity> reservationFotels = new ArrayList<>();

    public void changeState(ReservationState reservationState) {
        this.reservationState = reservationState;
    }
}