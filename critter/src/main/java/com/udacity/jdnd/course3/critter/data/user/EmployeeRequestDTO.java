package com.udacity.jdnd.course3.critter.data.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequestDTO {
    private Set<EmployeeSkill> skills;
    private LocalDate date;

    public EmployeeRequestDTO(Set<EmployeeSkill> skills, LocalDate date) {
        this.skills = skills;
        this.date = date;
    }
}
