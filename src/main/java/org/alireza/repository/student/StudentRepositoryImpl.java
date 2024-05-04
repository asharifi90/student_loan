package org.alireza.repository.student;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.connection.SessionFactorySingleton;
import org.alireza.model.Loan;
import org.alireza.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student, Long>
implements StudentRepository{
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public String getEntity() {
        return "Student";
    }

    @Override
    public Student findByUsername(String codeMelli) {
        Session session = SessionFactorySingleton.getInstance().getCurrentSession();
        String hql = "From Student s WHERE s.codeMelli = :codeMelli";
        Query<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("codeMelli", codeMelli);
        return query.getSingleResult();
    }
}
