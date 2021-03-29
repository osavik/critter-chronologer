package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        customerRepository.persist(customer);
        return customer;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById (Long id){
        return customerRepository.find(id);
    }

    public Customer getCustomerByPet(Pet pet){
        return customerRepository.findCustomerByPet(pet);
    }


}
