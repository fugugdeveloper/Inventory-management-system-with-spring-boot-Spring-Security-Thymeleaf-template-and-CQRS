package com.ktun.inventory_management_system.service.query;

import com.ktun.inventory_management_system.exceptions.ResourceNotFoundException;
import com.ktun.inventory_management_system.model.Customer;
import com.ktun.inventory_management_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerQueryService {
    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer with id: "+id+" is not found."));
    }
}
