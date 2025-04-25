package jdk8.predicate;

import jdk8.models.Employee;
import jdk8.utils.EmployeeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExEmp {

    public static void main(String[] args) {

        List<Employee> employees = EmployeeUtils.createDummyEmployees();
        Predicate<Employee> salaryPredicate = employee -> employee.getSalary() > 10000;
        System.out.println("Employees with more than 30000 salaries are: ");
        employees.stream().filter(salaryPredicate).forEach(System.out::println);

        System.out.println("Employees with manager designation are: ");
//        Predicate<Employee> managerPredicate = Employee::isManager;
        Predicate<Employee> managerPredicate = employee -> employee.isManager();
        employees.stream().filter(managerPredicate).forEach(System.out::println);
    }
}
