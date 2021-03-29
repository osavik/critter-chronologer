package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerService customerService;

    public Pet save(Pet pet){
        // 1. persist pet
        petRepository.persist(pet);

        // 2. update customer with persisted pet
        Customer customerPersisted = customerService.getCustomerById(pet.getCustomer().getId());
        customerPersisted.addPetToCustomer(pet);

        return pet;
    }

    public Pet getPetById(Long id){
        return petRepository.find(id);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAllPets();
    }

    public List<Pet> getPetByOwnerId(Long ownerId){
        return petRepository.findPetByOwnerId(ownerId);
    }

}
