package jdk8.function;

import jdk8.models.Employee;
import jdk8.utils.EmployeeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionEx003 {

    public static void main(String[] args) {
        List<Employee> employees = EmployeeUtils.createDummyEmployees();

        Function<List<Employee>, Double> calculateTotalSalary = employees1 -> {
            double totalSalary = 0;
            for (Employee employee : employees1) {
                totalSalary += employee.getSalary();
            }
            return totalSalary;
        };

        double total = calculateTotalSalary.apply(employees);
        System.out.println("Salary SUM: " +total);

    }
}
