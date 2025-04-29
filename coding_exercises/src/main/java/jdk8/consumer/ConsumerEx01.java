package jdk8.consumer;

import jdk8.models.Student;
import jdk8.utils.StudentUtil;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConsumerEx01 {

    public static void main(String[] args) {

        Consumer<String> printString = str -> System.out.println(str.toUpperCase());

        printString.accept("Hello World");

        Predicate<Student> isPassed = student -> student.getMarks() > 500;
        Function<Student, String> calculateGrade = student -> {
            if(isPassed.test(student)) {
                if(student.getMarks() <= 600) {
                    return "Grade C";
                }
                else if(student.getMarks() > 600 && student.getMarks() <= 700) {
                    return "Grade B";
                }
                else if(student.getMarks() > 700 && student.getMarks() <= 800) {
                    return "Grade A";
                }
                else {
                    return "Grade O";
                }
            }
            return "FAILED";
        };

        Consumer<List<Student>> printStudents = students -> {
            students.forEach(student -> {
                System.out.println(student.getName() + " - "+ student.getMarks() + " Grade: " + calculateGrade.apply(student));
            });
        };

        List<Student> students = StudentUtil.generateStudents();
        printStudents.accept(students);
    }
}
