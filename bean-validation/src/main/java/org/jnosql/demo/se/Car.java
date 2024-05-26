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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.nosql.Convert;
import org.jnosql.demo.se.converter.MonetaryAmountConverter;
import org.jnosql.demo.se.validation.CurrencyAccepted;
import org.jnosql.demo.se.validation.MonetaryMax;
import org.jnosql.demo.se.validation.MonetaryMin;

import javax.money.MonetaryAmount;
import java.util.function.Supplier;

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
    @Convert(MonetaryAmountConverter.class)
    private MonetaryAmount price;

    @Column
    @NotBlank
    private String model;

    @Column
    @NotBlank
    private String color;

    private Car(String plate, MonetaryAmount price, String model, String color) {
        this.plate = plate;
        this.price = price;
        this.model = model;
        this.color = color;
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

    public String getColor() {
        return color;
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

        private static final String DEFAULT_COLOR = "black";

        private String plate;

        private MonetaryAmount price;

        private String model;

        private String color = DEFAULT_COLOR;

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

        public CarBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder withColor(Supplier<String> color) {
            return withColor(color.get());
        }

        public Car build() {
            return new Car(plate, price, model, color);
        }
    }


}
