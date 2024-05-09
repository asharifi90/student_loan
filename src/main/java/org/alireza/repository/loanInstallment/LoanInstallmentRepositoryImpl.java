package org.alireza.repository.loanInstallment;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.Loan;
import org.alireza.model.LoanInstallment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class LoanInstallmentRepositoryImpl extends BaseRepositoryImpl<LoanInstallment, Long>
        implements LoanInstallmentRepository {
    public LoanInstallmentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<LoanInstallment> getEntityClass() {
        return LoanInstallment.class;
    }

    @Override
    public String getEntity() {
        return "LoanInstallment";
    }

    @Override
    public List<LoanInstallment> findByLoanId(Long loanId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM LoanInstallment l WHERE l.loan.id = :loanId";
        Query<LoanInstallment> loanInstallments = session.createQuery(hql, LoanInstallment.class);
        loanInstallments.setParameter("loanId", loanId);
        List<LoanInstallment> loanInstallmentList = loanInstallments.list();
        return loanInstallmentList;
    }

    @Override
    public List<LoanInstallment> paidInstallments(boolean paid, Long loanId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM LoanInstallment l WHERE l.paid = :paid AND l.loan.id = :loanId";
        Query<LoanInstallment> loanInstallmentQuery = session.createQuery(hql, LoanInstallment.class);
        loanInstallmentQuery.setParameter("paid", paid);
        loanInstallmentQuery.setParameter("loanId", loanId);
        List<LoanInstallment> loanInstallmentList = loanInstallmentQuery.list();
        return loanInstallmentList;
    }
}
