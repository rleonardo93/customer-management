package com.multicert.customermanagement.service;

import com.multicert.customermanagement.dao.CustomerDao;
import com.multicert.customermanagement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Transactional
    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Customer get(long id) {
        return customerDao.get(id);
    }

    @Override
    public Customer getByNif(int nif) {
        return customerDao.getByNif(nif);
    }

    @Override
    public Set<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Set<Customer> getByName(String name) {
        return customerDao.getByName(name);
    }

    @Override
    public Set<Customer> getByCity(String city) {
        return customerDao.getByCity(city);
    }

    @Transactional
    @Override
    public void delete(long id) {
        customerDao.delete(id);
    }
}
