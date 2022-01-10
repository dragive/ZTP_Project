package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.FotelEntity;
import org.hibernate.SessionFactory;

public final class FotelRepository extends DataBaseRepository<FotelEntity>{

    @Builder
    public FotelRepository(SessionFactory sessionFactory){
            super(FotelEntity.class,sessionFactory);
    }
}
