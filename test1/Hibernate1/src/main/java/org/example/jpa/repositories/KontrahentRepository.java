package org.example.jpa.repositories;


import lombok.Builder;
import org.example.jpa.entities.KinoEntity;
import org.example.jpa.entities.KontrahentEntity;
import org.hibernate.SessionFactory;

public final class KontrahentRepository extends DataBaseRepository<KontrahentEntity>{

    @Builder
    public KontrahentRepository(SessionFactory sessionFactory){
        super(KontrahentEntity.class,sessionFactory);
    }
}

