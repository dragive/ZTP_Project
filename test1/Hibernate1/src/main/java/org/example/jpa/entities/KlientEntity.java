package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Table(name = "KLIENT")
@Entity
@Getter
@Setter
@ToString
public class KlientEntity extends User{
    @Id
    @Column(name = "KLIENT_ID", nullable = false)
    private Long id;

    @Column(name = "IMIE", nullable = false, length = 100)
    private String imie;

    @Column(name = "NAZWISKO", nullable = false, length = 100)
    private String nazwisko;

    @Column(name = "LOGIN", nullable = false, length = 100)
    private String login;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "NR_TELEFONU", nullable = false, length = 20)
    private String nrTelefonu;

    @Column(name = "HASLO", nullable = false, length = 100)
    private String haslo;

    @Column(name = "ADRES", nullable = false, length = 100)
    private String adres;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "KLIENT_ID")
    @ToString.Exclude
    private List<RezerwacjaEntity> rezerwacje;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "KLIENT_ID")
    @ToString.Exclude
    private List<RezerwacjaTransakcjaEntity> rezerwacjeTransakcje;
}