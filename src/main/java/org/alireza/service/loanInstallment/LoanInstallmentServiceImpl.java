package org.alireza.service.loanInstallment;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.LoanInstallment;
import org.alireza.repository.loanInstallment.LoanInstallmentRepository;
import org.alireza.service.loan.LoanService;
import org.hibernate.SessionFactory;

public class LoanInstallmentServiceImpl extends BaseServiceImpl<LoanInstallment, Long, LoanInstallmentRepository>
implements LoanInstallmentService {
    public LoanInstallmentServiceImpl(LoanInstallmentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
