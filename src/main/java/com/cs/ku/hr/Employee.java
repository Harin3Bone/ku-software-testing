package com.cs.ku.hr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Employee {

    private String name;
    private int id;
    private double salary;

    public void raiseSalary(double rate) {
        salary = salary + (salary * rate);
    }

}
