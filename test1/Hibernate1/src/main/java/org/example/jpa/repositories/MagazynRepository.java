package org.example.jpa.repositories;


import lombok.Builder;
import org.example.jpa.entities.KontrahentEntity;
import org.example.jpa.entities.MagazynEntity;
import org.hibernate.SessionFactory;

public final class MagazynRepository extends DataBaseRepository<MagazynEntity>{

    @Builder
    public MagazynRepository(SessionFactory sessionFactory){
        super(MagazynEntity.class,sessionFactory);
    }
}

