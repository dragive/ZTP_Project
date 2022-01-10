package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.SalaEntity;
import org.hibernate.SessionFactory;

public final class SalaRepository extends DataBaseRepository<SalaEntity>{

    @Builder
    public SalaRepository(SessionFactory sessionFactory){
        super(SalaEntity.class,sessionFactory);
    }
}