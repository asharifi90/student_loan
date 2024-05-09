package org.alireza.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "LoanInstallment")
public class LoanInstallment extends BaseEntity<Long> {

    @ManyToOne
    private Loan loan;
    private LocalDate installmentDate;
    private double price;
    private boolean paid;

    public LoanInstallment(Long aLong, Loan loan, LocalDate installmentDate, double price, boolean paid) {
        super(aLong);
        this.loan = loan;
        this.installmentDate = installmentDate;
        this.price = price;
        this.paid = paid;
    }

    public LoanInstallment(Loan loan, LocalDate installmentDate, double price, boolean paid) {
        this.loan = loan;
        this.installmentDate = installmentDate;
        this.price = price;
        this.paid = paid;
    }

    public LoanInstallment() {
    }

    public String toString() {
        return
                ", installmentDate=" + this.getInstallmentDate() + "\n" +
                ", price=" + this.getPrice() + "\n" +
                ", paid=" + this.isPaid() + ")" + "\n";
    }
}
