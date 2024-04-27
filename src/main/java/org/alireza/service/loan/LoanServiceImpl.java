package org.alireza.service.loan;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.Loan;
import org.alireza.repository.loan.LoanRepository;
import org.hibernate.SessionFactory;

public class LoanServiceImpl extends BaseServiceImpl<Loan, Long, LoanRepository> implements LoanService {
    public LoanServiceImpl(LoanRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
