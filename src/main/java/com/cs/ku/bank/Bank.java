package com.cs.ku.bank;

import com.cs.ku.bank.service.DataService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
public class Bank {

    private String bankName;
    private final Map<Integer, Customer> customers = new HashMap<>();
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private DataService<Customer> customerDataService;

    public Bank(String bankName, DataService<Customer> customerDataService) {
        this.bankName = bankName;
        this.customerDataService = customerDataService;
    }

    public Bank(String bankName) {
        this.bankName = bankName;
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

    public void openAccount(BankAccount account) {
        accounts.put(account.getName(), account);
    }

    public void transfer(String from, String to, double amount) {
        accounts.get(from).withdraw(amount);
        accounts.get(to).deposit(amount);
    }

    public void giveInterestAll(double rate) {
        for (BankAccount account : accounts.values()) {
            log.info("Giving interest {} to account: {}", rate, account.getName());
            account.addInterest(rate);
        }
    }

}
