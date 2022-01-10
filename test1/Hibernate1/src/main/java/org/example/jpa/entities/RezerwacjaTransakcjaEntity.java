package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.dto.Klient;
import org.example.dto.mappers.OLD.KlientMappingService;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "REZERWACJA_TRANSAKCJA")
@Entity
@Getter
@Setter
@ToString
public class RezerwacjaTransakcjaEntity {
    @Id
    @Column(name = "TRANSAKCJA_ID", nullable = false)
    private Long id;

    @Column(name = "CZY_GOTOWKA", nullable = false)
    private Boolean czyGotowka = false;

    @Column(name = "CZY_KARTA", nullable = false)
    private Boolean czyKarta = false;

    @Column(name = "CENA", nullable = false)
    private Double cena;

    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    @JoinColumn(name="Klient_id")
    private KlientEntity klient;

    @ManyToOne
    @JoinColumn(name="Pracownik_id")
    private PracownikEntity pracownik;

    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "REZERWACJA_ID", nullable = false)
    private RezerwacjaEntity rezerwacja;


}