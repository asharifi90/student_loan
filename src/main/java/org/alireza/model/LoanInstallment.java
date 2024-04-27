package org.alireza.model;


import jakarta.persistence.ManyToOne;
import org.alireza.base.entity.BaseEntity;

import java.time.LocalDate;

public class LoanInstallment extends BaseEntity<Long> {

    @ManyToOne
    private Loan loan;
    private LocalDate installmentDate;
    private double price;
    private boolean paid;
    private boolean notPaid;
}
