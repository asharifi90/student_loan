package org.alireza.repository.spouse;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.Spouse;
import org.hibernate.SessionFactory;

public class SpouseRepositoryImpl extends BaseRepositoryImpl<Spouse, Long>
implements SpouseRepository{
    public SpouseRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Spouse> getEntityClass() {
        return Spouse.class;
    }

    @Override
    public String getEntity() {
        return "Spouse";
    }
}
