package com.multicert.customermanagement.service;

import com.multicert.customermanagement.model.Address;
import com.multicert.customermanagement.model.Customer;

import java.util.Set;

public interface CustomerService {

    void save(Customer customer);

    Customer get(long id);

    Customer getByNif(int nif);

    Set<Customer> getAll();

    Set<Customer> getByName(String name);

    Set<Customer> getByCity (String city);

    void delete(long id);
}
