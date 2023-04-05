package com.roadtoglory.entry.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long playerId;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "weekly_wage")
    private Long wage;

    /*
    NOTE: If the join is for a OneToOne or ManyToOne mapping using a foreign key mapping strategy,
    the foreign key column is in the table of the source entity or embeddable.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_club_code")
    private Club currentClub;


    /*
    NOTE:   @JoinColumn(name = "player_reference_id") - this is in target entity (in car table as FK)
     so, it will reside on car table as a foreign key
     NOTE: Important: If you don't specify the join column annotation, it will create a join table called
     player_car which will have player_id and car_model_id as the column created by default
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_reference_id")
    private List<Car> car;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "agent_code")
    private Agent playerAgent;

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

    public List<Car> getCar() {
        return car;
    }

    public Agent getPlayerAgent() {
        return playerAgent;
    }

    public void setPlayerAgent(Agent playerAgent) {
        this.playerAgent = playerAgent;
    }

    public void setCar(List<Car> car) {
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

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", nationality='" + nationality + '\'' +
                ", wage=" + wage +
                ", currentClub=" + currentClub +
                ", car=" + car +
                ", playerAgent=" + playerAgent +
                ", basicInfo=" + basicInfo +
                '}';
    }
}
