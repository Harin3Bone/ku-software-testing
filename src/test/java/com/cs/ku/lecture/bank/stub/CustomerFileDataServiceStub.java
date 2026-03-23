package com.cs.ku.lecture.bank.stub;

import com.cs.ku.lecture.bank.Customer;
import com.cs.ku.lecture.bank.service.DataService;

import java.util.ArrayList;
import java.util.List;

public class CustomerFileDataServiceStub implements DataService<Customer> {

    private final List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getAllData() {
        return this.customers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

}
