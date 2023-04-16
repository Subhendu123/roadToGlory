package com.roadtoglory.entry.controllers;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadtoglory.entry.dtos.InstructorInformationDTO;
import com.roadtoglory.entry.entity.Instructor;
import com.roadtoglory.entry.entity.InstructorDetail;
import com.roadtoglory.entry.repos.InstructorDetailsRepository;
import com.roadtoglory.entry.repos.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class InstructorMappingController {

    @Autowired
    private InstructorRepository instructorRepository;

//    @Autowired
//    private ObjectMapper objectMapper;
//
    @Autowired
    private InstructorDetailsRepository instructorDetailsRepository;

    @RequestMapping(value = "/onetoone/save", method = RequestMethod.POST)
    public Instructor persistInstructorInformation(@RequestBody InstructorInformationDTO studentAndCustomer) throws IOException {
        System.out.println(studentAndCustomer.getInstructor());
        InstructorDetail ins = studentAndCustomer.getInstructorDetails();
        System.out.println("Instructor Details !!! "+ins);
        Instructor instructor = studentAndCustomer.getInstructor();
        instructor.setInstructorDetails(ins);
        System.out.println("Instructor Information "+instructor);
        //one 2 one
        System.out.println("SAVING INSTRUCTOR!!");
        Instructor instructor1 = instructorRepository.save(instructor);
        System.out.println("Completed!!");
        return instructor1;
    }



    @RequestMapping(value = "/onetoone/bidirectional/save", method = RequestMethod.POST)
    public InstructorDetail persistingInstructorInformation(@RequestBody InstructorInformationDTO instructorInformationDTO) throws IOException {
        InstructorDetail instructorDetail = instructorInformationDTO.getInstructorDetails();
        Instructor instructor = instructorInformationDTO.getInstructor();
        instructorDetail.setInstructor(instructor);
        System.out.println("My input instr details should contain instructor info as well!! ");
        System.out.println("The details "+instructorDetail);
        return instructorDetail;
    }

    @RequestMapping(value = "/onetoone/bidirectional/fetch", method = RequestMethod.GET)
    public InstructorDetail getInstrcBiDirectional(@RequestBody InstructorInformationDTO instructorInformationDTO) throws IOException {
        InstructorDetail instructorDetail = instructorInformationDTO.getInstructorDetails();
        Instructor instructor = instructorInformationDTO.getInstructor();
        instructorDetail.setInstructor(instructor);
        System.out.println("My input instr details should contain instructor info as well!! ");
        System.out.println("The details "+instructorDetail);
        return instructorDetail;
    }



    @RequestMapping( value = "/onetoone/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCascade(@PathVariable("id") int id){
        Instructor instructor = instructorRepository.getReferenceById(id);
        System.out.println(instructor);
        System.out.println("Instructor Details "+instructor.getInstructorDetails());
        instructorRepository.delete(instructor);
        System.out.println("Instructor Deleted");
    }

}
