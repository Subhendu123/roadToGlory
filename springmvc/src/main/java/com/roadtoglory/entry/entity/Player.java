package com.roadtoglory.entry.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "weekly_wage")
    private Long wage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_club_code")
    private Club currentClub;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private ArrayList<Car> car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_info_id")
    private PersonalInformation basicInfo;


    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getWage() {
        return wage;
    }

    public void setWage(Long wage) {
        this.wage = wage;
    }

    public Club getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(Club currentClub) {
        this.currentClub = currentClub;
    }

    public ArrayList<Car> getCar() {
        return car;
    }

    public void setCar(ArrayList<Car> car) {
        this.car = car;
    }

    public PersonalInformation getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(PersonalInformation basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
