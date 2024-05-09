package org.alireza.service.loanInstallment;

import org.alireza.base.service.BaseService;
import org.alireza.model.Loan;
import org.alireza.model.LoanInstallment;

import java.util.List;

public interface LoanInstallmentService extends BaseService<LoanInstallment, Long> {
    List<LoanInstallment> findByLoanId(Long loanId);

    List<LoanInstallment> paidInstallments(boolean paid, Long loanId);
}
