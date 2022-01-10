package org.example.jpa.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RezerwacjaFotelEntityId implements Serializable {
    private static final long serialVersionUID = 2874018198749922673L;
    @Column(name = "FOTEL_ID", nullable = false)
    private Long fotelId;
    @Column(name = "REZERWACJA_ID", nullable = false)
    private Long rezerwacjaId;

    public Long getRezerwacjaId() {
        return rezerwacjaId;
    }

    public void setRezerwacjaId(Long rezerwacjaId) {
        this.rezerwacjaId = rezerwacjaId;
    }

    public Long getFotelId() {
        return fotelId;
    }

    public void setFotelId(Long fotelId) {
        this.fotelId = fotelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fotelId, rezerwacjaId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RezerwacjaFotelEntityId entity = (RezerwacjaFotelEntityId) o;
        return Objects.equals(this.fotelId, entity.fotelId) &&
                Objects.equals(this.rezerwacjaId, entity.rezerwacjaId);
    }
}