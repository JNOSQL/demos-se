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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DriverTest {

    private static Validator validator;

    private final CurrencyUnit usd = Monetary.getCurrency(Locale.US);

    private Car ferrari;

    @BeforeEach
    void setUp() {
        ferrari = Car.builder().withPlate("BRL-1234")
                .withModel("ferrari")
                .withPrice(Money.of(1000, usd))
                .build();
    }

    @BeforeAll
    public static void beforeAll() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }


    @Test
    public void shouldReturnErrorWhenDriverHasNotLicense() {

        Driver driver = Driver.builder().withAge(20)
                .withCars(Collections.singletonList(ferrari))
                .withEmail("email@email.com")
                .withLicense(false)
                .withName("Speed Racer").build();

        Set<ConstraintViolation<Driver>> validate = validator.validate(driver);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("A driver must have a license", message);
    }

    @Test
    public void shouldReturnErrorWhenDriverIsYoung() {

        Driver driver = Driver.builder().withAge(10)
                .withCars(Collections.singletonList(ferrari))
                .withEmail("email@email.com")
                .withLicense(true)
                .withName("Speed Racer").build();

        Set<ConstraintViolation<Driver>> validate = validator.validate(driver);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("Age should not be less than 18", message);
    }

    @Test
    public void shouldReturnErrorWhenDriverIsOld() {

        Driver driver = Driver.builder().withAge(200)
                .withCars(Collections.singletonList(ferrari))
                .withEmail("email@email.com")
                .withLicense(true)
                .withName("Speed Racer").build();

        Set<ConstraintViolation<Driver>> validate = validator.validate(driver);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("Age should not be greater than 150", message);
    }

    @Test
    public void shouldReturnErrorWhenDriverEmailIsInvalid() {

        Driver driver = Driver.builder().withAge(25)
                .withCars(Collections.singletonList(ferrari))
                .withEmail("emailemail")
                .withLicense(true)
                .withName("Speed Racer").build();

        Set<ConstraintViolation<Driver>> validate = validator.validate(driver);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("Email should be valid", message);
    }

    @Test
    public void shouldReturnErrorWhenDriverNotHaveCar() {

        Driver driver = Driver.builder().withAge(25)
                .withEmail("email@email.com")
                .withLicense(true)
                .withName("Speed Racer").build();

        Set<ConstraintViolation<Driver>> validate = validator.validate(driver);
        assertFalse(validate.isEmpty());
        String message = validate.stream().findFirst()
                .map(ConstraintViolation::getMessageTemplate)
                .orElse(null);
        assertEquals("It must have one car at least", message);
    }


    @Test
    public void shouldCreateInstance() {
        Driver driver = Driver.builder().withAge(25)
                .withCars(Collections.singletonList(ferrari))
                .withEmail("email@email.com")
                .withLicense(true)
                .withName("Speed Racer").build();

        Set<ConstraintViolation<Driver>> validate = validator.validate(driver);
        assertTrue(validate.isEmpty());
    }
}