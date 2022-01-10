package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "SEANS")
@Entity
@Getter
@Setter
@ToString
public class SeansEntity {
    @Id
    @Column(name = "SEANS_ID", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SALA_ID", nullable = false)
    private SalaEntity sala;

    @Column(name = "CENA", nullable = false)
    private Double cena;

    @Column(name = "GODZINA_ROZPOCZECIA", nullable = false)
    private LocalDateTime godzinaRozpoczecia;

    @Column(name = "GODZINA_ZAKONCZENIA", nullable = false)
    private LocalDateTime godzinaZakonczenia;

    @ManyToMany(fetch=FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Film_Seans",
            joinColumns = { @JoinColumn(name = "SEANS_ID") },
            inverseJoinColumns = { @JoinColumn(name = "FILM_ID") }
    )
    @ToString.Exclude
    private List<FilmEntity> seansFilms = new ArrayList<>();
}