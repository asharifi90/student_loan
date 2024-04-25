package org.alireza.model;

import jakarta.persistence.ManyToOne;
import org.alireza.base.entity.BaseEntity;

import java.time.LocalDate;

public class BankCard extends BaseEntity<Long> {

    private String bankName;
    private int cardNumber; //(Store just the last 4 numbers of card)
    private LocalDate expiryDate;

    @ManyToOne
    private Student student;
}
