package org.alireza.service.loan;

import org.alireza.base.service.BaseService;
import org.alireza.model.Enum.LoanType;
import org.alireza.model.Loan;

public interface LoanService extends BaseService<Loan, Long> {

    public Loan findByStudentId(Long studentId, LoanType loanType);
}
