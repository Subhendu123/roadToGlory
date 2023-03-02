package com.roadtoglory.entry.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

    public InstructorDetail() {
        System.out.println("No arg constructor for Instructor Details !!!!!");
    }

    public InstructorDetail(String youtubeChannel, String hobby, Instructor instructor) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
