package com.cs.ku.bank.service.impl;

import com.cs.ku.bank.Customer;
import com.cs.ku.bank.service.DataService;

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
