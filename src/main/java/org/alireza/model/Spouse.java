package org.alireza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
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
    private Date birthDate;
}
