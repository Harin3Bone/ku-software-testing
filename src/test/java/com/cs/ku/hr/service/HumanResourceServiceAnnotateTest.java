package com.cs.ku.hr.service;

import com.cs.ku.hr.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class HumanResourceServiceAnnotateTest {

    @InjectMocks
    private HumanResourceService hr;

    @Mock
    private Employee mockEmployee1;

    @Mock
    private Employee mockEmployee2;

    @BeforeEach
    void initMocks() {
        openMocks(this);
    }

    @Test
    void testRaiseSalaryAll() {
        double rate = 0.1;

        hr.addEmployee(mockEmployee1);
        hr.addEmployee(mockEmployee2);

        hr.raiseSalaryAll(rate);

        verify(mockEmployee1).raiseSalary(rate);
        verify(mockEmployee2).raiseSalary(rate);
    }

}
