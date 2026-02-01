package com.cs.ku.hr.service;

import com.cs.ku.hr.Employee;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HumanResourceServiceMockTest {

    @Test
    void testRaiseSalaryAll() {
        // create mock objects
        Employee mockEmployee1 = mock(Employee.class);
        Employee mockEmployee2 = mock(Employee.class);

        // create CUT and setting up
        HumanResourceService hr = new HumanResourceService();
        hr.addEmployee(mockEmployee1);
        hr.addEmployee(mockEmployee2);

        // call method under test
        hr.raiseSalaryAll(0.1);

        // ตรวจสอบที่ mock object
        verify(mockEmployee1).raiseSalary(0.1);
        verify(mockEmployee2).raiseSalary(0.1);
    }

}
