package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "KINO")
@Entity
@Getter
@Setter
@ToString
public class KinoEntity {
    @Id
    @Column(name = "KINO_ID", nullable = false)
    private Long id;

    @Column(name = "OPIS", nullable = false, length = 4000)
    private String opis;

    @Column(name = "ADRES", nullable = false, length = 100)
    private String adres;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "MIASTO", nullable = false)
    private String miasto;

    @OneToMany//(fetch = FetchType.LAZY)
    @JoinColumn(name = "kino_id")
    @ToString.Exclude
    private List<MagazynEntity> magazyny;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "kino_id")
    @ToString.Exclude
    private List<PracownikEntity> pracownicy;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "kino_kino_id")
    @ToString.Exclude
    private List<SalaEntity> sale;

}