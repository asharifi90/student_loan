package org.alireza.service.spouse;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.Spouse;
import org.alireza.repository.spouse.SpouseRepository;
import org.hibernate.SessionFactory;

public class SpouseServiceImpl extends BaseServiceImpl<Spouse, Long, SpouseRepository>
implements SpouseService{
    public SpouseServiceImpl(SpouseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
