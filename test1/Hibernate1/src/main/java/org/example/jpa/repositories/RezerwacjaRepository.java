package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.RezerwacjaEntity;
import org.hibernate.SessionFactory;

public final class RezerwacjaRepository extends DataBaseRepository<RezerwacjaEntity>{

    @Builder
    public RezerwacjaRepository(SessionFactory sessionFactory){
        super(RezerwacjaEntity.class,sessionFactory);
    }
}