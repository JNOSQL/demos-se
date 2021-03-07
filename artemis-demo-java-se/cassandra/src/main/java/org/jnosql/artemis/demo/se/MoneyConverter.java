package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.AttributeConverter;

import java.util.Currency;

public class MoneyConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency attribute) {
        return attribute.getSymbol();
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        return Currency.getAvailableCurrencies().stream()
                .filter(c -> dbData.equals(c.getCurrencyCode()))
                .findAny().orElse(null);
    }
}
