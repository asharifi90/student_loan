package org.alireza.service.loan;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.Enum.LoanType;
import org.alireza.model.Loan;
import org.alireza.repository.loan.LoanRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LoanServiceImpl extends BaseServiceImpl<Loan, Long, LoanRepository> implements LoanService {
    public LoanServiceImpl(LoanRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Loan findByStudentId(Long studentId, LoanType loanType) {
        try(Session session = sessionFactory.getCurrentSession()){
            Transaction t = session.beginTransaction();
            Loan loan = repository.findByStudentId(studentId, loanType);
            t.commit();
            return loan;
        }catch (Exception e){
            return null;
        }
    }
}