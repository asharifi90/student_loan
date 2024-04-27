package org.alireza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BankCard")
public class BankCard extends BaseEntity<Long> {

    private int cardNumber;
    private LocalDate expiryDate;
    private int CVV2;

    @ManyToOne
    private Student student;
}
