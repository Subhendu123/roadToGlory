package jdk8.models;

public class Employee {

    private String name;
    private int age;
    private double salary;
    public Employee(int age, double salary, String name) {
        this.age = age;
        this.salary = salary;
        this.name = name;
    }

    public boolean isManager() {
        return this.salary > 30000 && this.age > 30;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
