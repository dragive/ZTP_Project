package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "PRACOWNIK")
@Entity
@Getter
@Setter
@ToString
public class PracownikEntity extends User{
    @Id
    @Column(name = "PRACOWNIK_ID", nullable = false)
    private Long id;

    @Column(name = "IMIE", nullable = false, length = 100)
    private String imie;

    @Column(name = "NAZWISKO", nullable = false, length = 100)
    private String nazwisko;

    @Column(name = "LOGIN", nullable = false, length = 100)
    private String login;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "NR_TELEFONU", nullable = false, length = 100)
    private String nrTelefonu;

    @Column(name = "HASLO", nullable = false, length = 100)
    private String haslo;

    @Column(name = "RODZAJ_UMOWY", nullable = false, length = 100)
    private String rodzajUmowy;

    @Column(name = "CZY_KIEROWNIK", nullable = false)
    private Boolean czyKierownik = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "KINO_ID", nullable = false)
    private KinoEntity kino;

    @OneToMany
    @JoinColumn(name="przedmiot_transakcja_id"/*,insertable = false,updatable = false*/)
    private List<PrzedmiotTransakcjaEntity> transakcje;

    @OneToMany
    @JoinColumn(name="Pracownik_id"/*,insertable = false,updatable = false*/)
    private List<RezerwacjaTransakcjaEntity> Rezerwacje;

}