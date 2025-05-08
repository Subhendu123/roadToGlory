package jdk8.dblcolon;

import jdk8.func.interfaces.FuncInterface02;
import jdk8.models.Student;

public class DoubleClnOp02 {


    public static void main(String[] args) {

        // new approach to achieve the Constructor reference using :: operator
        FuncInterface02 funcInterface012 = Student::new;
        Student std = funcInterface012.getStudent("Alice", 100);
        System.out.println("std: " + std.getName()+ ", marks: " + std.getMarks());

        // old approach of Lambda expression
        FuncInterface02 funcInterface01 = (a, i) -> new Student(a, i);
        std = funcInterface01.getStudent("Paul", 500);
        System.out.println("std: " + std.getName()+ ", marks: " + std.getMarks());

    }
}
