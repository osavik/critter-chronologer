package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public List<Employee> getEmployeesForDay(LocalDate date){
        return employeeRepository.findEmployeesForDay(date);
    }
}
