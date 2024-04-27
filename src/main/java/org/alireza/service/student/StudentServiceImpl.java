package org.alireza.service.student;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.Student;
import org.alireza.repository.student.StudentRepository;
import org.hibernate.SessionFactory;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository>
implements StudentService{
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
