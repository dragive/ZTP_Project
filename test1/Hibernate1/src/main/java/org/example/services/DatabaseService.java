package org.example.services;

import lombok.Getter;
import org.example.jpa.entities.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class DatabaseService {
    private static DatabaseService instance;
    @Getter
    private Configuration configuration;

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        if(this.sessionFactory==null){
            this.sessionFactory=this.buildSessionFactory();
        }
        return this.sessionFactory;
    }

    private SessionFactory buildSessionFactory(){
        return this.configuration.buildSessionFactory();
    }

    private DatabaseService(){
        databaseConfiguration();
    }

    public static DatabaseService getInstance(){
        if(instance == null){
            instance = new DatabaseService();
        }
        return instance;
    }

    private void databaseConfiguration(){
        configuration = new Configuration().configure()
                .addAnnotatedClass(FilmEntity.class)
                .addAnnotatedClass(FilmSeanEntity.class)
                .addAnnotatedClass(FilmSeanEntityId.class)
                .addAnnotatedClass(FotelEntity.class)
                .addAnnotatedClass(KinoEntity.class)
                .addAnnotatedClass(KontrahentEntity.class)
                .addAnnotatedClass(MagazynEntity.class)
                .addAnnotatedClass(PracownikEntity.class)
                .addAnnotatedClass(PrzedmiotEntity.class)
                .addAnnotatedClass(PrzedmiotMagazynEntityId.class)
                .addAnnotatedClass(PrzedmiotMagazynEntity.class)
                .addAnnotatedClass(PrzedmiotPrzedmiotTransakcjaEntity.class)
                .addAnnotatedClass(PrzedmiotTransakcjaEntity.class)
                .addAnnotatedClass(PrzedmiotPrzedmiotTransakcjaEntityId.class)
                .addAnnotatedClass(RezerwacjaEntity.class)
                .addAnnotatedClass(RezerwacjaFotelEntity.class)
                .addAnnotatedClass(RezerwacjaTransakcjaEntity.class)
                .addAnnotatedClass(SalaEntity.class)
                .addAnnotatedClass(SeansEntity.class)
                .addAnnotatedClass(KlientEntity.class);
    }
}
