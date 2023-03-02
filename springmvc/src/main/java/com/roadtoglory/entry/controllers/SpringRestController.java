package com.roadtoglory.entry.controllers;

import com.roadtoglory.entry.entity.Student;
import com.roadtoglory.entry.repos.StudentRepository;
import com.sun.jdi.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SpringRestController {

    @Autowired
    private StudentRepository studentRepository;


    @RequestMapping("/getStudents")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @RequestMapping(path = "/saveStudent", method = RequestMethod.POST)
    public Student saveStudent(@RequestBody Student st){
        return studentRepository.save(st);
    }

    @RequestMapping(path = "/getStudent/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        System.out.println("The student id is : "+id);
        return studentRepository.findById(id).get();
    }

    @RequestMapping(path = "/deleteStudent/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id){
        studentRepository.deleteById(id);
    }

    @RequestMapping(path = "/updateStudent", method = RequestMethod.PUT)
    public Student updateById(@RequestParam("id") int id, @RequestBody Student student){
        student.setId(id);
        studentRepository.save(student);
        return studentRepository.findById(id).get();
    }


}
