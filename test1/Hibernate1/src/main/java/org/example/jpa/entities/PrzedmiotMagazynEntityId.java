package org.example.jpa.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrzedmiotMagazynEntityId implements Serializable {
    private static final long serialVersionUID = -3032781218884285765L;
    @Column(name = "MAGAZYN_ID", nullable = false)
    private Long magazynId;
    @Column(name = "PRZEDMIOT_ID", nullable = false)
    private Long przedmiotId;

    public Long getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(Long przedmiotId) {
        this.przedmiotId = przedmiotId;
    }

    public Long getMagazynId() {
        return magazynId;
    }

    public void setMagazynId(Long magazynId) {
        this.magazynId = magazynId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(magazynId, przedmiotId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrzedmiotMagazynEntityId entity = (PrzedmiotMagazynEntityId) o;
        return Objects.equals(this.magazynId, entity.magazynId) &&
                Objects.equals(this.przedmiotId, entity.przedmiotId);
    }
}