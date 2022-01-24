package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "PRZEDMIOT")
@Entity
@Getter
@Setter
@ToString
public class PrzedmiotEntity implements Cloneable {
    @Id
    @Column(name = "PRZEDMIOT_ID", nullable = false)
    private Long id;

    @Column(name = "RABAT")
    private Double rabat;

    @Column(name = "NAZWA", nullable = false, length = 100)
    private String nazwa;

    @Column(name = "KATEGORIA", nullable = false, length = 100)
    private String kategoria;

    @Column(name = "CENA", nullable = false)
    private Double cena;

    @Column(name = "CZY_NA_WYNOS", nullable = false)
    private Boolean czyNaWynos = false;

    @Column(name = "PRODUCENT", nullable = false, length = 100)
    private String producent;

    @Column(name = "DATA_WAZNOSCI")
    private LocalDateTime dataWaznosci;

    @OneToMany
    @JoinColumn(name="Przedmiot_id")
    @ToString.Exclude
    private List<PrzedmiotMagazynEntity> magazynyZIloscia;

    @OneToMany
    @JoinColumn(name="Przedmiot_id")
    @ToString.Exclude
    private List<PrzedmiotPrzedmiotTransakcjaEntity> transakcjeZPrzedmiotem;

    @ManyToMany(mappedBy = "przedmiotyTransakcji",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<PrzedmiotTransakcjaEntity> transakcjePrzedmiotu = new ArrayList<>();

    public PrzedmiotEntity() {}

    public PrzedmiotEntity(PrzedmiotEntity przedmiot) {
        this.id = przedmiot.id;
        this.rabat = przedmiot.rabat;
        this.nazwa = przedmiot.nazwa;
        this.kategoria = przedmiot.kategoria;
        this.cena = przedmiot.cena;
        this.czyNaWynos = przedmiot.czyNaWynos;
        this.producent = przedmiot.producent;
        this.dataWaznosci = przedmiot.dataWaznosci;
        this.magazynyZIloscia = przedmiot.magazynyZIloscia;
        this.transakcjeZPrzedmiotem = przedmiot.transakcjeZPrzedmiotem;
        this.transakcjePrzedmiotu = przedmiot.transakcjePrzedmiotu;
    }

    @Override
    public PrzedmiotEntity clone() throws CloneNotSupportedException {
        return new PrzedmiotEntity(this);
    }
}