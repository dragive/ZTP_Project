package org.example.jpa.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "FILM")
@Entity
@Getter
@Setter
@ToString
public class FilmEntity {
    @Id
    @Column(name = "FILM_ID", nullable = false)
    private Long id;

    @Column(name = "OPIS", nullable = false, length = 4000)
    private String opis;

    @Column(name = "CZAS_TRWANIA", nullable = false)
    private Double czasTrwania;

    @Column(name = "CZY_3D", nullable = false)
    private Boolean czy3d = false;

    @Column(name = "CZY_NAPISY", nullable = false)
    private Boolean czyNapisy = false;

    @Column(name = "CZY_DUBBING", nullable = false)
    private Boolean czyDubbing = false;

    @Column(name = "CZY_ORIGINAL", nullable = false)
    private Boolean czyOriginal = false;

    @Column(name = "WIEK", nullable = false)
    private Long wiek;

    @Column(name = "DATA_WYDANIA", nullable = false)
    private LocalDate dataWydania;

    @Column(name = "TITLE", nullable = true)
    private String title;

    @ManyToMany(mappedBy = "seansFilms")
    @ToString.Exclude
    private List<SeansEntity> seansEntities = new ArrayList<>();
}