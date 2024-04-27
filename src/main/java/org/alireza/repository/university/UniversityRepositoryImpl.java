package org.alireza.repository.university;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.Loan;
import org.alireza.model.University;
import org.hibernate.SessionFactory;

public class UniversityRepositoryImpl extends BaseRepositoryImpl<University, Long>
implements UniversityRepository{
    public UniversityRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<University> getEntityClass() {
        return University.class;
    }

    @Override
    public String getEntity() {
        return "University";
    }
}
