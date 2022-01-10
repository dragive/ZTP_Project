package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Table(name = "PRZEDMIOT_PRZEDMIOT_TRANSAKCJA")
@Entity
@Getter
@Setter
@ToString
public class PrzedmiotPrzedmiotTransakcjaEntity {
    @EmbeddedId
    private PrzedmiotPrzedmiotTransakcjaEntityId id;


    @ManyToOne
    @JoinColumn(name="Przedmiot_id")
    private PrzedmiotEntity przedmiotEntity;

    @ManyToOne
    @JoinColumn(name="przedmiot_transakcja_id")
    private PrzedmiotTransakcjaEntity przedmiotTransakcja;





}