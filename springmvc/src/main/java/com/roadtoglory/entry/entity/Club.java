package com.roadtoglory.entry.entity;

import javax.persistence.*;

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code", nullable = false)
    private Long code;

    @Column(name = "stadium_name" , nullable = false)
    private String stadiumName;

    @Column(name = "capacity")
    private long capacity;

    // personal info
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_info_id")
    private PersonalInformation personalInformation;


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }
}
