package org.alireza.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;
import org.alireza.model.Enum.City;
import org.alireza.model.Enum.EducationLevel;
import org.alireza.model.Enum.Gender;
import org.alireza.model.Enum.MaritalStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student extends BaseEntity<Long> {

    private String firstname;
    private String lastname;
    private String fatherName;
    private String motherName;
    private String password;
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

    @Enumerated(EnumType.STRING)
    private City city;

    @ManyToOne
    private University university;

    @OneToOne
    private Spouse spouse;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    private List<BankCard> bankCard;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REMOVE})
    private List<Loan> loan;

    public Student(Long aLong, String firstname, String lastname, String fatherName, String motherName, String password, String shomareShenasname, String codeMelli, LocalDate birthDate, String studentCode, int entryYear, EducationLevel educationLevel, MaritalStatus maritalStatus, boolean dormitoryResident, Gender gender, int contractNumber, City city, University university, Spouse spouse) {
        super(aLong);
        this.firstname = firstname;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.password = password;
        this.shomareShenasname = shomareShenasname;
        this.codeMelli = codeMelli;
        this.birthDate = birthDate;
        this.studentCode = studentCode;
        EntryYear = entryYear;
        this.educationLevel = educationLevel;
        this.maritalStatus = maritalStatus;
        this.dormitoryResident = dormitoryResident;
        this.gender = gender;
        this.contractNumber = contractNumber;
        this.city = city;
        this.university = university;
        this.spouse = spouse;
        this.bankCard = new ArrayList<>();
        this.loan = new ArrayList<>();
    }


    public Student(String firstname, String lastname, String fatherName, String motherName, String password, String shomareShenasname, String codeMelli, LocalDate birthDate, String studentCode, int EntryYear, EducationLevel educationLevel, MaritalStatus maritalStatus, boolean dormitoryResident, Gender gender, int contractNumber, City city, University university, Spouse spouse) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.password = password;
        this.shomareShenasname = shomareShenasname;
        this.codeMelli = codeMelli;
        this.birthDate = birthDate;
        this.studentCode = studentCode;
        this.EntryYear = EntryYear;
        this.educationLevel = educationLevel;
        this.maritalStatus = maritalStatus;
        this.dormitoryResident = dormitoryResident;
        this.gender = gender;
        this.contractNumber = contractNumber;
        this.city = city;
        this.university = university;
        this.spouse = spouse;
        this.bankCard = new ArrayList<>();
        this.loan = new ArrayList<>();
    }

    public Student() {
    }

    public String toString() {
        return "Student(firstname=" + this.getFirstname()
                + ", lastname=" + this.getLastname()
                + ", fatherName=" + this.getFatherName()
                + ", motherName=" + this.getMotherName()
                + ", password=" +this.getPassword()
                + ", shomareShenasname=" + this.getShomareShenasname()
                + ", codeMelli=" + this.getCodeMelli()
                + ", birthDate=" + this.getBirthDate()
                + ", studentCode=" + this.getStudentCode()
                + ", EntryYear=" + this.getEntryYear()
                + ", educationLevel=" + this.getEducationLevel()
                + ", maritalStatus=" + this.getMaritalStatus()
                + ", dormitoryResident=" + this.isDormitoryResident()
                + ", gender=" + this.getGender()
                + ", contractNumber=" + this.getContractNumber()
                + ", city=" + this.getCity()
                + ", university=" + this.getUniversity()
                + ", spouse=" + this.getSpouse() ;
//                + ", bankCard=" + this.getBankCard()
//                + ", loan=" + this.getLoan() + ")";
    }
}
