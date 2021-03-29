package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);
        pet.setId(null);
        petService.save(pet);

        return PetDTO.convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        Pet persistedPet = petService.getPetById(petId);

        return PetDTO.convertPetToPetDTO(persistedPet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List <Pet> pets = petService.getAllPets();

        List <PetDTO> petDTOList = new ArrayList<>();

        for (Pet pet: pets){
            petDTOList.add(PetDTO.convertPetToPetDTO(pet));
        }

        return petDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List <Pet> pets = petService.getPetByOwnerId(ownerId);

        List <PetDTO> petDTOList = new ArrayList<>();

        for (Pet pet: pets){
            petDTOList.add(PetDTO.convertPetToPetDTO(pet));
        }

        return petDTOList;

    }

    public Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        pet.setCustomer(customer);

        return pet;
    }
}
