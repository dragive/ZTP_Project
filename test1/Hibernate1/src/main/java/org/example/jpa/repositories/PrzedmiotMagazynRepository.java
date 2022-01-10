package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.PrzedmiotEntity;
import org.example.jpa.entities.PrzedmiotMagazynEntity;
import org.hibernate.SessionFactory;

public final class PrzedmiotMagazynRepository extends DataBaseRepository<PrzedmiotMagazynEntity>{

    @Builder
    public PrzedmiotMagazynRepository(SessionFactory sessionFactory){
        super(PrzedmiotMagazynEntity.class,sessionFactory);
    }
}