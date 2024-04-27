package org.alireza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;
import org.alireza.model.Enum.UniversityType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "University")
public class University extends BaseEntity<Long> {

    private String name;
    private String city;

    @Enumerated(EnumType.STRING)
    private UniversityType universityType;

    @OneToMany(mappedBy = "university", cascade = {CascadeType.PERSIST,
    CascadeType.MERGE, CascadeType.DETACH})
    private List<Student> student;
}
