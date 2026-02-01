package com.cs.ku.bank.service.impl;

import com.cs.ku.bank.Customer;
import com.cs.ku.bank.service.DataService;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomerFileDataService implements DataService<Customer> {

    private static final String CUSTOMER_FILES = "customers.txt";

    @Override
    public List<Customer> getAllData() {
        List<Customer> customers = new ArrayList<>();
        try {
            FileReader file = new FileReader(CUSTOMER_FILES);
            BufferedReader in = new BufferedReader(file);

            String line;
            while ((line = in.readLine()) != null) {
                String[] data = line.trim().split(",");
                int id = Integer.parseInt(data[0]);
                int pin = Integer.parseInt(data[1]);
                String name = data[2];
                Customer customer = new Customer(id, pin, name);
                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            log.error("File {} cannot be found", CUSTOMER_FILES);
        } catch (IOException e) {
            log.error("Error while reading from file {}", CUSTOMER_FILES);
        }

        return customers;
    }

}
