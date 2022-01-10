package org.example.jpa.repositories;


import lombok.Builder;
import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.PracownikEntity;
import org.hibernate.SessionFactory;

public final class PracownikRepository extends DataBaseRepository<PracownikEntity>{

    @Builder
    public PracownikRepository(SessionFactory sessionFactory){
        super(PracownikEntity.class,sessionFactory);
    }
}

