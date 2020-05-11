package com.multicert.customermanagement.controller;

import com.multicert.customermanagement.model.Customer;
import com.multicert.customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Add new Customer
    @PostMapping("/customers")
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        long id = customerService.save(customer);
        return ResponseEntity.ok().body("New Customer has been saved with ID: " + id);
    }

    // Delete a Customer by id
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> delete(@PathVariable("customerId") long id) {
        customerService.delete(id);
        return ResponseEntity.ok().body("Customer with ID: " + id + " has been deleted successfully");
    }

    // Get a Customer by id
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> get(@PathVariable("customerId") long id) {
        Customer customer = customerService.get(id);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    // Get all Customers
    @GetMapping("/customers")
    public ResponseEntity<Set<Customer>> list() {
        Set<Customer> customers = customerService.getAll();
        if (customers == null || customers.isEmpty()) {
            return new ResponseEntity<Set<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Customer>>(customers, HttpStatus.OK);
    }

    // Get Customer by NIF
    @RequestMapping(value = "/customers", params = "nif", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerByNIF(@RequestParam("nif") Optional<Integer> nif) {
        if (!nif.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerService.getByNif(nif.get());
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    // Get Customers by Name
    @RequestMapping(value = "/customers", params = "name", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerByName(@RequestParam("name") Optional<String> name) {
        if (!name.isPresent() || name.get().length() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Set<Customer> customers = customerService.getByName(name.get());
        if (customers == null || customers.isEmpty()) {
            return new ResponseEntity<Set<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Customer>>(customers, HttpStatus.OK);
    }

    // Get Customers by City
    @RequestMapping(value = "/customers", params = "city", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerByCity(@RequestParam("city") Optional<String> city) {
        if (!city.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Set<Customer> customers = customerService.getByCity(city.get());
        if (customers == null || customers.isEmpty()) {
            return new ResponseEntity<Set<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Customer>>(customers, HttpStatus.OK);
    }
}
