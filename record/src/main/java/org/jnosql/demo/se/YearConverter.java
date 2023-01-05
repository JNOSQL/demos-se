package org.jnosql.demo.se;

import jakarta.nosql.mapping.AttributeConverter;

import java.time.Year;

public class YearConverter implements AttributeConverter<Year, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Year attribute) {
        return attribute.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer dbData) {
        return Year.of(dbData);
    }
}
