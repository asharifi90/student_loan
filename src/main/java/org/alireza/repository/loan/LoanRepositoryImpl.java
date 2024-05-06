package org.alireza.repository.loan;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.connection.SessionFactorySingleton;
import org.alireza.model.Enum.LoanType;
import org.alireza.model.Loan;
import org.alireza.model.Student;
import org.alireza.service.loan.LoanService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LoanRepositoryImpl extends BaseRepositoryImpl<Loan, Long>
implements LoanRepository{
    public LoanRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public String getEntity() {
        return "Loan";
    }

    @Override
    public Loan findByStudentId(Long studentId, LoanType loanType) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Loan l WHERE l.student.id = :studentId AND l.loanType = :loanType";
        Query<Loan> loanQuery = session.createQuery(hql, Loan.class);
        loanQuery.setParameter("studentId", studentId);
        loanQuery.setParameter("loanType", loanType);
        Loan loan = loanQuery.getSingleResult();
        return loan;
    }
}
