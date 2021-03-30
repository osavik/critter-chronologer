package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.data.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule scheduleToSave = convertScheduleDTOToSchedule(scheduleDTO);
        scheduleToSave.setId(null);
        Schedule schedulePersisted = scheduleService.save(scheduleToSave);

        return convertScheduleToScheduleDTO(schedulePersisted);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List <Schedule> schedules = scheduleService.findAllSchedules();

        List <ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule : schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        // firstly get Pet by petId
        Pet pet = petService.getPetById(petId);

        List <Schedule> schedules = scheduleService.findAllSchedulesForPet(pet);

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule : schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        // firstly get Employee by employeeId
        Employee employee = employeeService.getEmployeeById(employeeId);

        List <Schedule> schedules = scheduleService.findAllSchedulesForEmployee(employee);

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule : schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        // firstly, get all pets for customer
        List <Pet> pets = petService.getPetByOwnerId(customerId);

        // retrieve all schedules for each pet
        // is there any better solution ?!
        List <Schedule> schedules = new ArrayList<>();
        for(Pet pet : pets){
            schedules.addAll(scheduleService.findAllSchedulesForPet(pet));
        }

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule : schedules){
            scheduleDTOS.add(convertScheduleToScheduleDTO(schedule));
        }

        return scheduleDTOS;
    }

    public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        // need to fill List<Employee> employees;
        // & List<Pet> pets;
        List <Employee> employees = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        for (Long id : scheduleDTO.getEmployeeIds()){
            employees.add(employeeService.getEmployeeById(id));
        }

        for (Long id : scheduleDTO.getPetIds()){
            pets.add(petService.getPetById(id));
        }

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return schedule;
    }

    public ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Long> employeeIds = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();

        for (Employee employee : schedule.getEmployees()){
            employeeIds.add(employee.getId());
        }

        for (Pet pet : schedule.getPets()){
            petIds.add(pet.getId());
        }

        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);

        return scheduleDTO;
    }
}
