package org.example.jpa.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "FILM_SEANS")
@Entity
@Getter
@Setter
public class FilmSeanEntity {
    @EmbeddedId
    private FilmSeanEntityId id;

}