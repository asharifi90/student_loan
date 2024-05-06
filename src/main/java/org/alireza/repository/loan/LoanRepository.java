package org.alireza.repository.loan;

import org.alireza.base.repository.BaseRepository;
import org.alireza.model.Enum.LoanType;
import org.alireza.model.Loan;
import org.alireza.model.Student;

public interface LoanRepository extends BaseRepository<Loan, Long> {

    Loan findByStudentId(Long studentId, LoanType loanType);

}
