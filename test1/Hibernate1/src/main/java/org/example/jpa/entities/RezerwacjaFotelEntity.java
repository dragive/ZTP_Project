package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "REZERWACJA_FOTEL")
@Entity
@Getter
@Setter
@ToString
public class RezerwacjaFotelEntity {
    @EmbeddedId
    private RezerwacjaFotelEntityId id = new RezerwacjaFotelEntityId();

}