package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.PracownikEntity;
import org.example.jpa.entities.PrzedmiotEntity;
import org.hibernate.SessionFactory;

public final class PrzedmiotRepository extends DataBaseRepository<PrzedmiotEntity>{

    @Builder
    public PrzedmiotRepository(SessionFactory sessionFactory){
        super(PrzedmiotEntity.class,sessionFactory);
    }
}