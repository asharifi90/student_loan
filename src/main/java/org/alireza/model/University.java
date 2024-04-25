package org.alireza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;

import java.security.PrivateKey;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "University")
public class University extends BaseEntity<Long> {

    private String name;
    private String city;
    private UniversityType universityType;
}
