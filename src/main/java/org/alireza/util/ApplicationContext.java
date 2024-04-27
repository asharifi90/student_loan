package org.alireza.util;

import org.alireza.connection.SessionFactorySingleton;
import org.alireza.repository.bankCard.BankCardRepository;
import org.alireza.repository.bankCard.BankCardRepositoryImpl;
import org.alireza.repository.loan.LoanRepository;
import org.alireza.repository.loan.LoanRepositoryImpl;
import org.alireza.repository.loanInstallment.LoanInstallmentRepository;
import org.alireza.repository.loanInstallment.LoanInstallmentRepositoryImpl;
import org.alireza.repository.spouse.SpouseRepository;
import org.alireza.repository.spouse.SpouseRepositoryImpl;
import org.alireza.repository.student.StudentRepository;
import org.alireza.repository.student.StudentRepositoryImpl;
import org.alireza.repository.university.UniversityRepository;
import org.alireza.repository.university.UniversityRepositoryImpl;
import org.alireza.service.bankCard.BankCardService;
import org.alireza.service.bankCard.BankCardServiceImpl;
import org.alireza.service.loan.LoanService;
import org.alireza.service.loan.LoanServiceImpl;
import org.alireza.service.loanInstallment.LoanInstallmentService;
import org.alireza.service.loanInstallment.LoanInstallmentServiceImpl;
import org.alireza.service.spouse.SpouseService;
import org.alireza.service.spouse.SpouseServiceImpl;
import org.alireza.service.student.StudentService;
import org.alireza.service.student.StudentServiceImpl;
import org.alireza.service.university.UniversityService;
import org.alireza.service.university.UniversityServiceImpl;
import org.hibernate.SessionFactory;

public class ApplicationContext {

    static final SessionFactory SESSION_FACTORY;
    private static final BankCardRepository BANK_CARD_REPOSITORY;
    private static final BankCardService BANK_CARD_SERVICE;

    private static final UniversityRepository UNIVERSITY_REPOSITORY;
    private static final UniversityService UNIVERSITY_SERVICE;

    private static final LoanRepository LOAN_REPOSITORY;
    private static final LoanService LOAN_SERVICE;

    private static final LoanInstallmentRepository LOAN_INSTALLMENT_REPOSITORY;
    private static final LoanInstallmentService LOAN_INSTALLMENT_SERVICE;

    private static final SpouseRepository SPOUSE_REPOSITORY;
    private static final SpouseService SPOUSE_SERVICE;

    private static final StudentRepository STUDENT_REPOSITORY;
    private static final StudentService STUDENT_SERVICE;

    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();

        BANK_CARD_REPOSITORY = new BankCardRepositoryImpl(SESSION_FACTORY);
        BANK_CARD_SERVICE = new BankCardServiceImpl(BANK_CARD_REPOSITORY, SESSION_FACTORY);

        UNIVERSITY_REPOSITORY = new UniversityRepositoryImpl(SESSION_FACTORY);
        UNIVERSITY_SERVICE = new UniversityServiceImpl(UNIVERSITY_REPOSITORY, SESSION_FACTORY);

        LOAN_REPOSITORY = new LoanRepositoryImpl(SESSION_FACTORY);
        LOAN_SERVICE = new LoanServiceImpl(LOAN_REPOSITORY, SESSION_FACTORY);

        LOAN_INSTALLMENT_REPOSITORY = new LoanInstallmentRepositoryImpl(SESSION_FACTORY);
        LOAN_INSTALLMENT_SERVICE = new LoanInstallmentServiceImpl(LOAN_INSTALLMENT_REPOSITORY, SESSION_FACTORY);

        SPOUSE_REPOSITORY = new SpouseRepositoryImpl(SESSION_FACTORY);
        SPOUSE_SERVICE = new SpouseServiceImpl(SPOUSE_REPOSITORY, SESSION_FACTORY);

        STUDENT_REPOSITORY = new StudentRepositoryImpl(SESSION_FACTORY);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY, SESSION_FACTORY);
    }

    public static BankCardService getBankCardService(){
        return BANK_CARD_SERVICE;
    }

    public static UniversityService getUniversityService(){
        return UNIVERSITY_SERVICE;
    }

    public static LoanService getLoanService(){
        return LOAN_SERVICE;
    }

    public static LoanInstallmentService getLoanInstallmentService(){
        return LOAN_INSTALLMENT_SERVICE;
    }

    public static SpouseService getSpouseService(){
        return SPOUSE_SERVICE;
    }

    public static StudentService getStudentService(){
        return STUDENT_SERVICE;
    }
}
