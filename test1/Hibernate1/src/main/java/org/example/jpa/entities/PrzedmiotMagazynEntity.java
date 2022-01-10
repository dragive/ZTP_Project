package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "PRZEDMIOT_MAGAZYN")
@Entity
@Getter
@Setter
@ToString
public class PrzedmiotMagazynEntity {
    @EmbeddedId
    private PrzedmiotMagazynEntityId id = new PrzedmiotMagazynEntityId();

    @Column(name = "ILOSC", nullable = false)
    private Long ilosc;

    @ManyToOne
    @JoinColumn(name="Magazyn_id",insertable = false, updatable = false)
    private MagazynEntity magazyn;

    @ManyToOne
    @JoinColumn(name="Przedmiot_id",insertable = false, updatable = false)
    private PrzedmiotEntity przedmiot;

}