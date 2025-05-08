package jdk8.streams;

import jdk8.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalEx01 {
    public static void main(String[] args) {
       Student student = new Student("Tom", 22);
       student = null;
       Optional<Student> studentOptional = Optional.ofNullable(student);
       System.out.println(studentOptional); // returns / prints Optional.empty



       Optional.ofNullable(student)
               .ifPresentOrElse(student1 -> {
                           System.out.println("This is in the if block");
                           System.out.println("Student is Found: " + student1.getName());
                       },
                       () -> {
                           System.out.println("This is in the else block");
                           System.out.println("Student Not found");
                       }
                       );

       student = null;
       Optional<Student> optional = Optional.ofNullable(student);
       String s = optional.map(Student::getName).orElse(null);
        System.out.println("Student's name is " + s);
       Integer i = optional.map(Student::getMarks).orElse(0);
        System.out.println("Student's marks is " + i);

        // Concise code for the same
        int marksObtained = Optional.ofNullable(student).map(Student::getMarks).orElse(0);
        System.out.println("Concise cpdeStudent's marks is " + marksObtained);

        String studentName = Optional.ofNullable(student).map(Student::getName).orElse("Unknown Name");
        System.out.println("Concise Student's name is " + studentName);

        Optional<String> emptyOpt = Optional.empty();

        System.out.println(emptyOpt.isPresent()); // false
        System.out.println(emptyOpt.orElse("Default")); // "Default"

    }
}
