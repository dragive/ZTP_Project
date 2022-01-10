package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.PrzedmiotPrzedmiotTransakcjaEntity;
import org.example.jpa.entities.PrzedmiotTransakcjaEntity;
import org.example.jpa.entities.SeansEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public final class ProductTransactionRepository extends DataBaseRepository<PrzedmiotTransakcjaEntity>{
    @Builder
    public ProductTransactionRepository(SessionFactory sessionFactory){
       super(PrzedmiotTransakcjaEntity.class,sessionFactory);
    }

}
