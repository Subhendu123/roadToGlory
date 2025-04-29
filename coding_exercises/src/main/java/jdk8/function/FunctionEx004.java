package jdk8.function;

import jdk8.models.Employee;
import jdk8.utils.EmployeeUtils;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionEx004 {

    public static void main(String[] args) {
        List<Employee> employees = EmployeeUtils.createDummyEmployees();

        Predicate<Employee> isSalIncrementRequired = employee -> employee.getSalary() < 45000;


        Function<Employee, Double> incrementSal = employee -> {
            double increment = 0;
                if (isSalIncrementRequired.test(employee)) {
                    double empSalary = employee.getSalary();
                    increment = empSalary * .1;
                    empSalary+= increment;
                    employee.setSalary(empSalary);
                }
            return increment;
        };

        employees.forEach(employee -> {
            double increment = incrementSal.apply(employee);
            System.out.println("Employee "+employee.getName()+" new Salary: " + employee.getSalary() + " Increment: " + increment);
        });

    }
}
