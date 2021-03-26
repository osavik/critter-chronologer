package com.udacity.jdnd.course3.critter.data.pet;

/**
 * A example list of pet type metadata that could be included on a request to create a pet.
 */

/**
 *to persist enum we are using JPA 2.1 @Converter Annotation
 * https://www.baeldung.com/jpa-persisting-enums-in-jpa
 */

public enum PetType {


    CAT("CAT"), DOG("DOG"), LIZARD("LIZARD"), BIRD("BIRD"),
    FISH("FISH"), SNAKE("SNAKE"), OTHER("OTHER");

    private String code;

    private PetType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
