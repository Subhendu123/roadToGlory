package function;

import jdk8.models.Student;
import jdk8.utils.StudentUtil;

import java.util.ArrayList;
import java.util.function.Function;

public class FunctionEx002 {

    public static void main(String[] args) {

        ArrayList<Student> students = StudentUtil.generateStudents();

        Function<Student,String> gradeCalculator = student -> {
            if(student.getMarks() >= 700){
                return "A";
            }
            else if(student.getMarks() < 700 && student.getMarks() >= 600){
                return "B";
            }
            else if(student.getMarks() < 600 && student.getMarks() >= 400){
                return "C";
            }
            else {
                return "D";
            }
        };

        students.forEach(student -> System.out.println("Grade for "+student.getName()+" : "+gradeCalculator.apply(student)+ " and marks "+student.getMarks()));
    }
}
