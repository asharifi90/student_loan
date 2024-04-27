package org.alireza.repository.student;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.Loan;
import org.alireza.model.Student;
import org.hibernate.SessionFactory;

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
}
