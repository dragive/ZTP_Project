package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "PRZEDMIOT_TRANSAKCJA")
@Entity
@Getter
@Setter
@ToString
public class PrzedmiotTransakcjaEntity {
    @Id
    @Column(name = "PRZEDMIOT_TRANSAKCJA_ID", nullable = false)
    private Long id;

    @Column(name = "CZY_KARTA", nullable = false)
    private Boolean czyKarta = false;

    @Column(name = "CZY_GOTOWKA", nullable = false)
    private Boolean czyGotowka = false;

    @Column(name = "CENA", nullable = false)
    private Double cena;

    @Column(name = "CZY_FAKTURA", nullable = false)
    private Boolean czyFaktura = false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "KONTRAHENT_ID",nullable = true)
    private KontrahentEntity kontrahent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRACOWNIK_ID", nullable = false)
    private PracownikEntity pracownik;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "przedmiot_przedmiot_transakcja",
            joinColumns = { @JoinColumn(name = "przedmiot_transakcja_id") },
            inverseJoinColumns = { @JoinColumn(name = "przedmiot_id") }
    )
    @ToString.Exclude
    private List<PrzedmiotEntity> przedmiotyTransakcji = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "MAGAZYN_ID", nullable = false)
    private MagazynEntity magazyn;


}