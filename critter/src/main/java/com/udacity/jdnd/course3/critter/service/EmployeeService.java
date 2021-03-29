package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        employeeRepository.persist(employee);
        return employee;
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.find(id);
    }

    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable){
        employeeRepository.changeAvailability(employeeId, daysAvailable);
    }

    public List<Employee> getEmployeesForService(EmployeeRequestDTO employeeRequestDTO){
        // 1. firstly ,find all employees for this day
        List<Employee> employeesForDay =  employeeRepository.findEmployeesForDay(employeeRequestDTO.getDate());

        // 2. threshold employees by skills
        List<Employee> employeesForService = new ArrayList<>();
        for(Employee employee : employeesForDay){
            if(employee.getSkills().containsAll(employeeRequestDTO.getSkills())){
                employeesForService.add(employee);
            }
        }

        return employeesForService;
    }

}
