package org.alireza.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;
import org.alireza.model.Enum.City;
import org.alireza.model.Enum.UniversityType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "University")
public class University extends BaseEntity<Long> {

    private String name;
    private City city;

    @Enumerated(EnumType.STRING)
    private UniversityType universityType;

    @OneToMany(mappedBy = "university", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH})
    private List<Student> student;

    public University(Long aLong, String name, City city, UniversityType universityType) {
        super(aLong);
        this.name = name;
        this.city = city;
        this.universityType = universityType;
        this.student = new ArrayList<>();
    }

    public University(String name, City city, UniversityType universityType) {
        this.name = name;
        this.city = city;
        this.universityType = universityType;
        this.student = new ArrayList<>();
    }

    public University() {
    }


    public String toString() {
        return "University(name=" + this.getName() +
                ", city=" + this.getCity() +
                ", universityType=" + this.getUniversityType() ;
//                ", student=" + this.getStudent() + ")";
    }
}
