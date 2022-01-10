package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "MAGAZYN")
@Entity
@Getter
@Setter
@ToString
public class MagazynEntity {
    @Id
    @Column(name = "MAGAZYN_ID", nullable = false)
    private Long id;

    @Column(name = "OPIS", nullable = false, length = 4000)
    private String opis;

    @Column(name = "ADRES", nullable = false, length = 100)
    private String adres;

    @Column(name = "MIASTO", nullable = false, length = 100)
    private String miasto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "KINO_ID", nullable = false)
    private KinoEntity kino;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="Magazyn_id")
    @ToString.Exclude
    private List<PrzedmiotMagazynEntity> przedmiotyWMagazynie;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "MAGAZYN_ID")
    @ToString.Exclude
    private List<PrzedmiotTransakcjaEntity> transakcje;
}