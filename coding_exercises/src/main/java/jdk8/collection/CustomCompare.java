package jdk8.collection;

import jdk8.models.Employee;
import jdk8.utils.ComparatorUtil;

import java.util.ArrayList;

public class CustomCompare {

    public static void main(String[] args) {
        Employee e1 = new Employee(40, 50000, "A");
        Employee e2 = new Employee(20, 10000, "B");
        Employee e3 = new Employee(34, 40000, "C");
        Employee e4 = new Employee(45, 71000, "D");
        Employee e5 = new Employee(56, 180000, "E");
        Employee e6 = new Employee(37, 90000, "F");
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);
        employees.add(e6);

        System.out.println("SALARY SORTING......");

        employees.sort(ComparatorUtil.getEmployeeComparator("salary"));
        employees.forEach(System.out::println);

        System.out.println("AGE SORTING......");

        employees.sort(ComparatorUtil.getEmployeeComparator("age"));
        employees.forEach(System.out::println);
    }
}
