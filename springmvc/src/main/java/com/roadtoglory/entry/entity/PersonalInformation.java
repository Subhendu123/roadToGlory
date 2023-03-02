package com.roadtoglory.entry.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personal_information")
public class PersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirthOrInception;

    @Column(name = "city")
    private String city;

    // TODO
    // Leave for one to one mapping for bi-directional case. It is independent.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirthOrInception() {
        return dateOfBirthOrInception;
    }

    public void setDateOfBirthOrInception(Date dateOfBirthOrInception) {
        this.dateOfBirthOrInception = dateOfBirthOrInception;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
