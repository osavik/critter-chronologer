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
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection(targetClass=EmployeeSkill.class)
    @Enumerated(EnumType.STRING) // default is to ORDINAL.
    @CollectionTable(name="employee")
    @Column(name="skills") // Column name in employee
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass=DayOfWeek.class)
    @Enumerated(EnumType.STRING) // default is to ORDINAL.
    @CollectionTable(name="employee")
    @Column(name="daysAvailable") // Column name in employee
    private Set<DayOfWeek> daysAvailable;

    public Employee(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }
}
