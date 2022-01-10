package org.example.jpa.repositories;

import lombok.Builder;

import org.example.jpa.entities.FilmEntity;
import org.hibernate.SessionFactory;

public final class FilmRepository extends DataBaseRepository<FilmEntity>{

    @Builder
    public FilmRepository(SessionFactory sessionFactory){
       super(FilmEntity.class,sessionFactory);

    }

}
