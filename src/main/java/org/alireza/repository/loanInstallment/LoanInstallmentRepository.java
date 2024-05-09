package org.alireza.repository.loanInstallment;

import org.alireza.base.repository.BaseRepository;
import org.alireza.model.Loan;
import org.alireza.model.LoanInstallment;

import java.util.List;

public interface LoanInstallmentRepository extends BaseRepository<LoanInstallment, Long> {

    List<LoanInstallment> findByLoanId(Long loanId);
    List<LoanInstallment> paidInstallments(boolean paid, Long loanId);
}
