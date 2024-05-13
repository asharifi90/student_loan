package org.alireza.service.student;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.connection.SessionFactorySingleton;
import org.alireza.model.Student;
import org.alireza.repository.student.StudentRepository;
import org.alireza.repository.student.StudentRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.nio.channels.SeekableByteChannel;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository>
implements StudentService{
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Student signIn(String codeMelli, String password) {
        try(Session session = sessionFactory.getCurrentSession()){
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

    @Override
    public Student findByCodeMelli(String codeMelli) {
        try(Session session = sessionFactory.getCurrentSession()){
            Transaction t = session.beginTransaction();
            Student student = repository.findByUsername(codeMelli);
            t.commit();
            return student;
        }catch (Exception e){
            return null;
        }
    }
}
