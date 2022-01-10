package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.SeansEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public final class SeansRepository extends DataBaseRepository<SeansEntity>{
    @Builder
    public SeansRepository(SessionFactory sessionFactory){
       super(SeansEntity.class,sessionFactory);
    }

    public List<FilmEntity> getFilmsOfSeans(Long id){
        Session session= sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List ret = this.getById(id).getSeansFilms();

        transaction.commit();
        session.close();
        return ret;
    }
}
