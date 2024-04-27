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
    private int price;

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
}
