package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmSeanEntityId implements Serializable {
    private static final long serialVersionUID = -8545886870962450564L;
    @Column(name = "FILM_ID", nullable = false)
    @Getter
    @Setter
    private Long filmId;
    @Column(name = "SEANS_ID", nullable = false)
    @Getter
    @Setter
    private Long seansId;

    @Override
    public int hashCode() {
        return Objects.hash(filmId, seansId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FilmSeanEntityId entity = (FilmSeanEntityId) o;
        return Objects.equals(this.filmId, entity.filmId) &&
                Objects.equals(this.seansId, entity.seansId);
    }
}