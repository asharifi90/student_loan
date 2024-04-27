package org.alireza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;
import org.alireza.model.Enum.EducationLevel;
import org.alireza.model.Enum.Gender;
import org.alireza.model.Enum.MaritalStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student extends BaseEntity<Long> {

    private String firstname;
    private String lastname;
    private String fatherName;
    private String motherName;
    private String shomareShenasname;
    private String codeMelli;
    private LocalDate birthDate;
    private String studentCode;
    private int EntryYear;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private boolean dormitoryResident;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int contractNumber;
    private String address;

    @ManyToOne
    private University university;

    @OneToOne(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REMOVE})
    private Spouse spouse;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST,
    CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    private List<BankCard> bankCard;

}
