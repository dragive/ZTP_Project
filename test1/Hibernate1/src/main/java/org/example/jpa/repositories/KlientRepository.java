package org.example.jpa.repositories;

import lombok.Builder;
import org.example.jpa.entities.KlientEntity;
import org.hibernate.SessionFactory;

public class KlientRepository extends DataBaseRepository<KlientEntity>{
    @Builder
    public KlientRepository(SessionFactory sessionFactory){
        super(KlientEntity.class,sessionFactory);
    }
}
