package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.RezerwacjaFotelEntity;
import org.hibernate.SessionFactory;

public final class RezerwacjaFotelRepository extends DataBaseRepository<RezerwacjaFotelEntity>{

    @Builder
    public RezerwacjaFotelRepository(SessionFactory sessionFactory){
        super(RezerwacjaFotelEntity.class,sessionFactory);
    }
}