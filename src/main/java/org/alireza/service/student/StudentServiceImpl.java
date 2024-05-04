package org.alireza.service.student;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.connection.SessionFactorySingleton;
import org.alireza.model.Student;
import org.alireza.repository.student.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository>
implements StudentService{
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Student signIn(String codeMelli, String password) {
        try(Session session = SessionFactorySingleton.getInstance().getCurrentSession()){
            Transaction t = session.beginTransaction();
            Student s = repository.findByUsername(codeMelli);
            if(s != null && s.getPassword().equals(password)){
                t.commit();
                return s;
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }
}
