package org.alireza.service.student;

import org.alireza.base.service.BaseService;
import org.alireza.model.Student;

public interface StudentService extends BaseService<Student, Long> {

    Student signIn(String codeMelli, String password);
}
