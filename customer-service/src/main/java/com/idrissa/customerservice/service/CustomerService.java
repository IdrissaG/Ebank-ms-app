package com.idrissa.customerservice.service;

import com.idrissa.customerservice.entities.Customer;
import com.idrissa.customerservice.repositories.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get all customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @McpTool(description = "Find a customer by his id")
    public Customer findCustomerById(@McpToolParam(description = "the customer id") Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
    }

    @McpTool(description = "Save a customer")
    public Customer saveCustomer(@McpToolParam(description = "Save a customer with his name and email the id is generated automatically") Customer customer){
        return customerRepository.save(customer);
    }
}
