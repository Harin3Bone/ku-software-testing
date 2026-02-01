package com.cs.ku.hr.service;

import com.cs.ku.hr.Employee;

import java.util.ArrayList;
import java.util.List;

public class HumanResourceService {

    private final List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void raiseSalaryAll(double rate) {
        for (Employee employee : employeeList) {
            employee.raiseSalary(rate);
        }
    }

}
