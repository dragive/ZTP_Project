package org.example.jpa.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrzedmiotPrzedmiotTransakcjaEntityId implements Serializable {
    private static final long serialVersionUID = -2779423688061697551L;
    @Column(name = "PRZEDMIOT_ID", nullable = false)
    private Long przedmiotId;
    @Column(name = "PRZEDMIOT_TRANSAKCJA_ID", nullable = false)
    private Long przedmiotTransakcjaId;

    public Long getPrzedmiotTransakcjaId() {
        return przedmiotTransakcjaId;
    }

    public void setPrzedmiotTransakcjaId(Long przedmiotTransakcjaId) {
        this.przedmiotTransakcjaId = przedmiotTransakcjaId;
    }

    public Long getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(Long przedmiotId) {
        this.przedmiotId = przedmiotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(przedmiotId, przedmiotTransakcjaId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrzedmiotPrzedmiotTransakcjaEntityId entity = (PrzedmiotPrzedmiotTransakcjaEntityId) o;
        return Objects.equals(this.przedmiotId, entity.przedmiotId) &&
                Objects.equals(this.przedmiotTransakcjaId, entity.przedmiotTransakcjaId);
    }
}