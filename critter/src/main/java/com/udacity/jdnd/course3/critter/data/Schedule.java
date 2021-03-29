package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.data.user.EmployeeSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;

    private LocalDate date;

    //@ElementCollection(targetClass = EmployeeSkill.class)
    //@Enumerated(EnumType.STRING) // default is to ORDINAL.
    //@CollectionTable(name="schedule")
    //@Column(name="activities") // Column name in schedule

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable(name = "schedule_activities", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(value = EnumType.STRING)
    private Set<EmployeeSkill> activities;


    public Schedule(Long id, List<Employee> employees, List<Pet> pets,
                    LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }
}
