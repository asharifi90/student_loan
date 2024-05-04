package org.alireza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;
import org.alireza.model.Enum.EducationLevel;
import org.alireza.model.Enum.LoanType;
import org.alireza.model.Enum.PaymentMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Loan")
public class Loan extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
    private Long price;
    private LocalDate loanDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String city;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @ManyToOne
    private Student student;

    @OneToMany(mappedBy = "loan", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    private List<LoanInstallment> loanInstallments;

    public Loan(Long aLong, EducationLevel educationLevel, Long price, PaymentMethod paymentMethod, String city, LoanType loanType, Student student) {
        super(aLong);
        this.educationLevel = educationLevel;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.city = city;
        this.loanType = loanType;
        this.student = student;
        this.loanInstallments = new ArrayList<>();
    }

    public Loan(EducationLevel educationLevel, Long price, PaymentMethod paymentMethod, String city, LoanType loanType, Student student) {
        this.educationLevel = educationLevel;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.city = city;
        this.loanType = loanType;
        this.student = student;
        this.loanInstallments = new ArrayList<>();
    }

    public String toString() {
        return "Loan(educationLevel=" + this.getEducationLevel() +
                ", price=" + this.getPrice() +
                ", paymentMethod=" + this.getPaymentMethod() +
                ", city=" + this.getCity() +
                ", loanType=" + this.getLoanType() +
                ", student=" + this.getStudent();
//                ", loanInstallments=" + this.getLoanInstallments() + ")";
    }
}
