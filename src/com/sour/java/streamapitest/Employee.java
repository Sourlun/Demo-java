package com.sour.java.streamapitest;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String name;

    private int age;

    private String sex;

    private double salary;

    public static List<Employee> getEmployeeList() {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("爱上防护", 12, "男", 2220.00));
        employeeList.add(new Employee("士大夫", 42, "男", 2750.00));
        employeeList.add(new Employee("士大夫试试", 64, "男", 2520.00));
        employeeList.add(new Employee("试试是", 12, "女", 5520.00));
        employeeList.add(new Employee("听音乐", 56, "男", 6620.00));
        employeeList.add(new Employee("绕太阳", 44, "男", 2520.00));
        employeeList.add(new Employee("东方化", 32, "女", 77120.00));
        employeeList.add(new Employee("士大夫试试", 64, "男", 2520.00));
        employeeList.add(new Employee("的风格", 23, "女", 22120.00));
        employeeList.add(new Employee("问我刚刚", 79, "男", 3250.00));
        employeeList.add(new Employee("开个会", 66, "女", 1250.00));
        employeeList.add(new Employee("士大夫试试", 64, "男", 2520.00));

        return employeeList;
    }


    public Employee(String name, int age, String sex, double salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                '}';
    }
}
