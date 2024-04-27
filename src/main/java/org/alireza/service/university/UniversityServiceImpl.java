package org.alireza.service.university;


import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.University;
import org.alireza.repository.university.UniversityRepository;
import org.hibernate.SessionFactory;

public class UniversityServiceImpl extends BaseServiceImpl<University, Long, UniversityRepository>
implements UniversityService{
    public UniversityServiceImpl(UniversityRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
