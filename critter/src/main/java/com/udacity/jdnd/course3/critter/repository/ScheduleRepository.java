package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository <Schedule, Long> {

    @Query("select s from Schedule s where :pet member of s.pets")
    List<Schedule> findAllSchedulesForPet(Pet pet);

    @Query("select s from Schedule s where :employee member of s.employees")
    List<Schedule> findAllSchedulesForEmployee(Employee employee);
}
