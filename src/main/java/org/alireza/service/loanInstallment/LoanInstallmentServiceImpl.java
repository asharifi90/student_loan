package org.alireza.service.loanInstallment;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.Loan;
import org.alireza.model.LoanInstallment;
import org.alireza.repository.loanInstallment.LoanInstallmentRepository;
import org.alireza.service.loan.LoanService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LoanInstallmentServiceImpl extends BaseServiceImpl<LoanInstallment, Long, LoanInstallmentRepository>
implements LoanInstallmentService {
    public LoanInstallmentServiceImpl(LoanInstallmentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<LoanInstallment> findByLoanId(Long loanId) {
        try (Session session = sessionFactory.getCurrentSession()){
            Transaction t = session.beginTransaction();
            List<LoanInstallment> loanInstallmentList = repository.findByLoanId(loanId);
            t.commit();
            return loanInstallmentList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<LoanInstallment> paidInstallments(boolean paid, Long loanId) {
        try(Session session = sessionFactory.getCurrentSession()) {
            Transaction t = session.beginTransaction();
            List<LoanInstallment> loanInstallmentList = repository.paidInstallments(paid, loanId);
            t.commit();
            return loanInstallmentList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
