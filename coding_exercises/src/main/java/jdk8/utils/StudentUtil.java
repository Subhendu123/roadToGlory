package jdk8.utils;

import jdk8.models.Student;

import java.util.ArrayList;

public class StudentUtil {

    public static ArrayList<Student> generateStudents(){

        ArrayList<Student> students = new ArrayList<>();

        Student student = new Student("Ram", 600);
        Student student2 = new Student("Rahim", 500);
        Student student3 = new Student("Chris", 700);
        Student student4 = new Student("Adam", 400);
        Student student5 = new Student("James", 300);
        Student student6 = new Student("Abdul", 200);
        Student student7 = new Student("Rahul", 800);
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        return students;
    }
}
