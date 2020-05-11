package com.multicert.customermanagement.controller;

import com.multicert.customermanagement.model.Customer;
import com.multicert.customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
        customerService.save(customer);
        return ResponseEntity.ok().body("New Customer has been saved");
    }

    // Delete a Customer by id
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> delete(@PathVariable("customerId") long id) {
        customerService.delete(id);
        return ResponseEntity.ok().body("Book has been deleted successfully");
    }

    // Get a Customer by id
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> get(@PathVariable("customerId") long id) {
        Customer customer = customerService.get(id);
        return ResponseEntity.ok().body(customer);
    }

    // Get all Customers
    @GetMapping("/customers")
    public ResponseEntity<Set<Customer>> list() {
        Set<Customer> customers = customerService.getAll();
        return ResponseEntity.ok().body(customers);
    }

    // Get Customer by NIF
    @RequestMapping(value = "/customers", params = "nif")
    public ResponseEntity<?> getCustomerByNIF(@RequestParam("nif") Optional<Integer> nif) {
        if (!nif.isPresent()) {
            return ResponseEntity.badRequest().body("NIF parameter is empty.");
        }
        Customer customer = customerService.getByNif(nif.get());
        return ResponseEntity.ok().body(customer);
    }

    // Get Customer by Name
    @RequestMapping(value = "/customers", params = "name")
    public ResponseEntity<?> getCustomerByName(@RequestParam("name") Optional<String> name) {
        if (!name.isPresent() || name.get().length() == 0) {
            return ResponseEntity.badRequest().body("Name parameter is empty.");
        }
        Set<Customer> customer = customerService.getByName(name.get());
        return ResponseEntity.ok().body(customer);
    }

    // Get Customer by City
    @RequestMapping(value = "/customers", params = "city")
    public ResponseEntity<?> getCustomerByCity(@RequestParam("city") Optional<String> city) {
        if (!city.isPresent()) {
            return ResponseEntity.badRequest().body("Name parameter is empty.");
        }
        Set<Customer> customer = customerService.getByCity(city.get());
        return ResponseEntity.ok().body(customer);
    }
}
