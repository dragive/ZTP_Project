package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.PrzedmiotTransakcjaEntity;
import org.example.jpa.entities.RezerwacjaTransakcjaEntity;
import org.hibernate.SessionFactory;

public final class RezerwacjaTranzakcjaRepository extends DataBaseRepository<RezerwacjaTransakcjaEntity>{
    @Builder
    public RezerwacjaTranzakcjaRepository(SessionFactory sessionFactory){
        super(RezerwacjaTransakcjaEntity.class,sessionFactory);
    }

}

