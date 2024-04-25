package org.alireza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;
import org.alireza.model.Enum.EducationLevel;
import org.alireza.model.Enum.LoanType;
import org.alireza.model.Enum.PaymentMethod;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Loan")
public class Loan extends BaseEntity<Long> {

    private EducationLevel educationLevel;
    private int price;
    private PaymentMethod paymentMethod;
    private String city;
    private LoanType loanType;
}
