package org.example.jpa.repositories;

import lombok.Builder;

import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.SeansEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public final class FilmRepository extends DataBaseRepository<FilmEntity>{

    @Builder
    public FilmRepository(SessionFactory sessionFactory){
       super(FilmEntity.class,sessionFactory);

    }

    /*public List<SeansEntity> getFilmSeanses(Long id){
        Session session= sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List ret = this.getById(id).getSeansEntities();

        transaction.commit();
        session.close();
        return ret;
    }*/
}
