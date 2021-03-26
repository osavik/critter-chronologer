package com.udacity.jdnd.course3.critter.data.pet;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * set the @Converter‘s value of autoApply to true so that JPA will
 * automatically apply the conversion logic to all mapped attributes of a PetType.
 */

@Converter(autoApply = true)
public class PetConverter implements AttributeConverter<PetType, String> {

    @Override
    public String convertToDatabaseColumn(PetType petType) {
        if (petType == null) {
            return null;
        }
        return petType.getCode();
    }

    @Override
    public PetType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(PetType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}