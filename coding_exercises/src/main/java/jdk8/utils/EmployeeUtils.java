package jdk8.utils;

import jdk8.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtils {

    public static List<Employee> createDummyEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(24,10000, "John"));
        employees.add(new Employee(24,20000, "Jane"));
        employees.add(new Employee(34,30000, "Rohit"));
        employees.add(new Employee(44,40000, "Jack"));
        employees.add(new Employee(35,50000, "James"));
        employees.add(new Employee(36,60000, "Rohan"));
        return employees;
    }
}
