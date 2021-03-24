package com.udacity.jdnd.course3.critter.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phoneNumber;

    private String notes;

    private List<Long> petIds;

}
