package org.alireza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.alireza.base.entity.BaseEntity;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@Entity
@Table(name = "Spouse")
public class Spouse extends BaseEntity<Long> {

    private String firstname;
    private String lastname;
    private String fatherName;
    private String motherName;
    private String codeMelli;
    private LocalDate birthDate;

    @OneToOne
    private Student student;

    public Spouse(Long aLong, String firstname, String lastname, String fatherName, String motherName, String codeMelli, LocalDate birthDate, Student student) {
        super(aLong);
        this.firstname = firstname;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.codeMelli = codeMelli;
        this.birthDate = birthDate;
        this.student = student;
    }

    public Spouse(String firstname, String lastname, String fatherName, String motherName, String codeMelli, LocalDate birthDate, Student student) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.codeMelli = codeMelli;
        this.birthDate = birthDate;
        this.student = student;
    }

    public Spouse() {
    }
}
