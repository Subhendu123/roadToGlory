package com.roadtoglory.entry.dtos;

import com.roadtoglory.entry.entity.Instructor;
import com.roadtoglory.entry.entity.InstructorDetail;

public class InstructorInformationDTO {

    private Instructor instructor;
    private InstructorDetail instructorDetail;

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public InstructorDetail getInstructorDetails() {
        return instructorDetail;
    }

    public void setInstructorDetails(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }
}
