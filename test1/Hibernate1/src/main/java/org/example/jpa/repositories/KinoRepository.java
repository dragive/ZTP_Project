package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.KinoEntity;
import org.hibernate.SessionFactory;

public final class KinoRepository extends DataBaseRepository<KinoEntity>{

    @Builder
    public KinoRepository(SessionFactory sessionFactory){
            super(KinoEntity.class,sessionFactory);
    }

}
