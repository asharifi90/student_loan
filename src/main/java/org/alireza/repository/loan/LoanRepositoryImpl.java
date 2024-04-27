package org.alireza.repository.loan;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.Loan;
import org.hibernate.SessionFactory;

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
}
