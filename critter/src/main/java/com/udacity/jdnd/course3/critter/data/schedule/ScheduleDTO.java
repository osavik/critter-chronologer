package com.udacity.jdnd.course3.critter.data.schedule;

import com.udacity.jdnd.course3.critter.data.user.EmployeeSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
@NoArgsConstructor
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public ScheduleDTO(long id, List<Long> employeeIds, List<Long> petIds,
                       LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employeeIds = employeeIds;
        this.petIds = petIds;
        this.date = date;
        this.activities = activities;
    }
}
