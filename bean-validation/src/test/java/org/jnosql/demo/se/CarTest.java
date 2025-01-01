/*
 * Copyright (c) 2019 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */
package org.jnosql.demo.se;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarTest {

    private static Validator validator;

    private final CurrencyUnit usd = Monetary.getCurrency(Locale.US);

    @BeforeAll
    public static void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }


    @Test
    public void shouldReturnErrorWhenCarInvalidPlate() {
        Car ferrari = Car.builder().withPlate("23-234")
                .withModel("ferrari")
                .withPrice(Money.of(1_0001, usd))
                .build();

        Set<ConstraintViolation<Car>> validate = validator.validate(ferrari);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("Invalid car plate", message);

    }


    @Test
    public void shouldReturnErrorWhenCarCheap() {
        Car ferrari = Car.builder().withPlate("BRL-1234")
                .withModel("ferrari")
                .withPrice(Money.of(99, usd))
                .build();

        Set<ConstraintViolation<Car>> validate = validator.validate(ferrari);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("There is not car cheap like that", message);
    }


    @Test
    public void shouldReturnErrorWhenCarFancy() {
        Car ferrari = Car.builder().withPlate("BRL-1234")
                .withModel("ferrari")
                .withPrice(Money.of(1_0000_000_000L, usd))
                .build();

        Set<ConstraintViolation<Car>> validate = validator.validate(ferrari);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("The parking does not support fancy car", message);

    }


    @Test
    public void shouldReturnErrorWhenCurrencyIsNotDollar() {

        CurrencyUnit real = Monetary.getCurrency("BRL");

        Car ferrari = Car.builder().withPlate("BRL-1234")
                .withModel("ferrari")
                .withPrice(Money.of(1000, real))
                .build();


        Set<ConstraintViolation<Car>> validate = validator.validate(ferrari);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("The car price must work with USD", message);

    }

    @Test
    public void shouldCreateInstance() {
        Car ferrari = Car.builder().withPlate("BRL-1234")
                .withModel("ferrari")
                .withPrice(Money.of(1000, usd))
                .build();

        Set<ConstraintViolation<Car>> validate = validator.validate(ferrari);
        assertTrue(validate.isEmpty());
    }
}