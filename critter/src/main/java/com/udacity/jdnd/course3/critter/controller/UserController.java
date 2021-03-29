package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.data.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.data.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = CustomerDTO.convertCustomerDTOToCustomer(customerDTO);
        customer.setId(null); // otherwise it will be 0
        Customer customerPersisted = customerService.save(customer);

        return CustomerDTO.convertCustomerToCustomerDTO(customerPersisted);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customers){
            customerDTOList.add(CustomerDTO.convertCustomerToCustomerDTO(customer));
        }

        return customerDTOList;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.getPetById(petId);
        Customer customer = customerService.getCustomerByPet(pet);

        return CustomerDTO.convertCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
