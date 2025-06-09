package com.ktun.inventory_management_system.service.command;

import com.ktun.inventory_management_system.model.Customer;
import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommandService {
    @Autowired
    CustomerRepository customerRepository;
    public SaveResponse addCustomer(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        if(savedCustomer.getCustomerId() ==null){
            return new SaveResponse(false, "Customer adding failed.");
        }
        return new SaveResponse(true, "Customer added successfully.");
    }
    public SaveResponse updateCustomer(Customer customer){
        Customer updatedCustomer = customerRepository.save(customer);
        if(updatedCustomer.getCustomerId() ==null){
            return new SaveResponse(false, "Customer update failed.");
        }
        return new SaveResponse(true, "Customer updated successfully.");
    }
}
