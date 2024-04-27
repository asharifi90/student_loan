package org.alireza.repository.loanInstallment;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.LoanInstallment;
import org.hibernate.SessionFactory;

public class LoanInstallmentRepositoryImpl extends BaseRepositoryImpl<LoanInstallment, Long>
implements LoanInstallmentRepository{
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
}
