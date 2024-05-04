package org.alireza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.alireza.base.entity.BaseEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "BankCard")
public class BankCard extends BaseEntity<Long> {

    private String bankName;
    private Long cardNumber;
    private LocalDate expiryDate;
    private int CVV2;
    private long balance;

    @ManyToOne
    private Student student;

    public BankCard(Long aLong, Long cardNumber, LocalDate expiryDate, int CVV2, Student student) {
        super(aLong);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.CVV2 = CVV2;
        this.student = student;
    }

    public BankCard(String bankName, Long cardNumber, LocalDate expiryDate, int CVV2) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.CVV2 = CVV2;
    }

    public BankCard(String bankName, Long cardNumber, LocalDate expiryDate, int CVV2, long balance) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.CVV2 = CVV2;
        this.balance = balance;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCVV2(int CVV2) {
        this.CVV2 = CVV2;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
