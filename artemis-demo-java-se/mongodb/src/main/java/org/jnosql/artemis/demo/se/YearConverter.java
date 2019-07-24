package org.jespanol.conference;

import jakarta.nosql.mapping.AttributeConverter;

import java.time.Year;

public class YearConverter implements AttributeConverter<Integer, Year> {

    @Override
    public Year convertToDatabaseColumn(Integer attribute) {
        if (attribute != null) {
            return Year.of(attribute);
        }
        return null;
    }

    @Override
    public Integer convertToEntityAttribute(Year dbData) {
        if (dbData != null) {
            return dbData.getValue();
        }
        return null;
    }

}
