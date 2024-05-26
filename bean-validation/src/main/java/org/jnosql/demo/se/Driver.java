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

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.jnosql.databases.mongodb.mapping.ObjectIdConverter;
import jakarta.nosql.Convert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Driver {


    @Id
    @Convert(ObjectIdConverter.class)
    private String id;

    @NotBlank(message = "Name cannot be null")
    @Column
    private String name;

    @AssertTrue(message = "A driver must have a license")
    @Column
    private boolean license;


    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    @Column
    private int age;

    @Email(message = "Email should be valid")
    @NotNull
    @Column
    private String email;


    @Size(min = 1, max = 5, message = "It must have one car at least")
    @NotNull
    @Column
    private List<Car> cars;

    @Deprecated
    Driver() {
    }

    private Driver(String name, boolean license, int age, String email, List<Car> cars) {
        this.name = name;
        this.license = license;
        this.age = age;
        this.email = email;
        this.cars = cars;
    }

    public void add(Car car) {
        this.cars.add(Objects.requireNonNull(car, "car is required"));
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", license=" + license +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", cars=" + cars +
                '}';
    }

    public static DriverBuilder builder() {
        return new DriverBuilder();
    }

    public static class DriverBuilder {

        private String name;

        private boolean license;

        private int age;

        private String email;

        private List<Car> cars = Collections.emptyList();

        private DriverBuilder() {
        }

        public DriverBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DriverBuilder withLicense(boolean license) {
            this.license = license;
            return this;
        }

        public DriverBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public DriverBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public DriverBuilder withCars(List<Car> cars) {
            this.cars = cars;
            return this;
        }

        public Driver build() {
            return new Driver(name, license, age, email, cars);
        }
    }


}