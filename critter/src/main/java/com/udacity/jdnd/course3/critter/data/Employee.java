package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.data.user.EmployeeSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(value = EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass=DayOfWeek.class)
    @CollectionTable(name = "days_available", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(value = EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public Employee(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }
}
