package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "FOTEL")
@Entity
@Getter
@Setter
@ToString
public class FotelEntity {
    @Id
    @Column(name = "FOTEL_ID", nullable = false)
    private Long id;

    @Column(name = "RZAD", nullable = false)
    private Long rzad;

    @Column(name = "NUMER", nullable = false)
    private Long numer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SALA_ID", nullable = false)
    private SalaEntity salaEntity;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Rezerwacja_Fotel",
            joinColumns = { @JoinColumn(name = "FOTEL_ID") },
            inverseJoinColumns = { @JoinColumn(name = "REZERWACJA_ID") }
    )
    @ToString.Exclude
    private List<RezerwacjaEntity> fotelReservations = new ArrayList<>();

}