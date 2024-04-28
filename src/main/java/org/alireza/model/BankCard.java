package org.alireza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.alireza.base.entity.BaseEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "BankCard")
public class BankCard extends BaseEntity<Long> {

    private Long cardNumber;
    private LocalDate expiryDate;
    private int CVV2;

    @ManyToOne
    private Student student;

    public BankCard(Long aLong, Long cardNumber, LocalDate expiryDate, int CVV2, Student student) {
        super(aLong);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.CVV2 = CVV2;
        this.student = student;
    }
}
