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
package org.jnosql.artemis.demo.se.parking;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.demo.se.parking.validation.CurrencyAccepted;
import org.jnosql.artemis.demo.se.parking.validation.MonetaryMax;
import org.jnosql.artemis.demo.se.parking.validation.MonetaryMin;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Car {

    @Column
    @NotNull
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "Invalid car plate")
    private String plate;

    @Column
    @NotNull
    @MonetaryMin(value = "100", message = "There is not car cheap like that")
    @MonetaryMax(value = "1000000", message = "The parking does not support fancy car")
    @CurrencyAccepted(currencies = "USD", message = "The car price must work with USD")
    private MonetaryAmount price;

    @Column
    @NotBlank
    private String model;

    private Car(String plate, MonetaryAmount price, String model) {
        this.plate = plate;
        this.price = price;
        this.model = model;
    }

    @Deprecated
    Car() {
    }

    public String getPlate() {
        return plate;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plate='" + plate + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                '}';
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public static class CarBuilder {

        private String plate;

        private MonetaryAmount price;

        private String model;

        private CarBuilder() {
        }

        public CarBuilder withPlate(String plate) {
            this.plate = plate;
            return this;
        }

        public CarBuilder withPrice(MonetaryAmount price) {
            this.price = price;
            return this;
        }

        public CarBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public Car build() {
            return new Car(plate, price, model);
        }
    }


}
