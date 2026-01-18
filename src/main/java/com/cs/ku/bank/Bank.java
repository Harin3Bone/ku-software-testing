package com.cs.ku.bank;

import com.cs.ku.bank.service.DataService;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Bank {

    private String bankName;
    private Map<Integer, Customer> customers;
    private DataService<Customer> customerDataService;

    public Bank(String bankName, DataService<Customer> customerDataService) {
        this.bankName = bankName;
        this.customers = new HashMap<>();
        this.customerDataService = customerDataService;
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer findCustomerById(int id) {
        return customers.get(id);
    }

    public boolean validateCustomer(int customerId, int pin) {
        Customer customer = findCustomerById(customerId);
        return customer != null && customer.match(pin);
    }

    public List<Customer> getCustomerFromFiles() {
        return customerDataService.getAllData();
    }

}
