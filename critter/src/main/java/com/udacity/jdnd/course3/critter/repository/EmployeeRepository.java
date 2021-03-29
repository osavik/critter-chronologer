package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.user.EmployeeRequestDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Employee employee){
        entityManager.persist(employee); // employee object is persisted and has id
    }

    public Employee find(Long id){
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }


    public Employee merge(Employee employee){
        Employee managedEmployee = entityManager.merge(employee);
        return managedEmployee;
    }

    public void delete(Long id){
        Employee employeeToDelete = entityManager.find(Employee.class, id);
        entityManager.remove(employeeToDelete);
    }

    public void changeAvailability(long employeeId, Set<DayOfWeek> daysAvailable){
        Employee employee = find(employeeId);
        employee.setDaysAvailable(daysAvailable);
    }

    public List<Employee> findEmployeesForDay(LocalDate localDate){

        return entityManager
                .createQuery("SELECT e FROM Employee e WHERE :day member of e.daysAvailable", Employee.class)
                .setParameter("day", localDate.getDayOfWeek())
                .getResultList();

    }

}
