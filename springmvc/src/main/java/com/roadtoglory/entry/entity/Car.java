package com.roadtoglory.entry.entity;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "model_id", nullable = false)
    private Long modelId;

    @Column(name = "mileage")
    private String mileage;

    @Column(name = "top_speed")
    private String topSpeed;

    @Column(name = "gears")
    private int noOfGears;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_info_id")
    private PersonalInformation basicInfo;


    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getNoOfGears() {
        return noOfGears;
    }

    public void setNoOfGears(int noOfGears) {
        this.noOfGears = noOfGears;
    }

    public PersonalInformation getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(PersonalInformation basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
