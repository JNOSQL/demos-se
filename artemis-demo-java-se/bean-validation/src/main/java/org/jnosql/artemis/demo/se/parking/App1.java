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


import org.javamoney.moneta.Money;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class App1 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            DriverRepository repository = container.select(DriverRepository.class).get();
            CurrencyUnit usd = Monetary.getCurrency(Locale.US);

            Car ferrari = Car.builder().withPlate("BRL-1234")
                    .withModel("ferrari")
                    .withColor(Color.RED)
                    .withPrice(Money.of(1000, usd))
                    .build();

            Driver driver = Driver.builder().withAge(25)
                    .withCars(Arrays.asList(ferrari))
                    .withEmail("email@email.com")
                    .withLicense(true)
                    .withName("Speed Racer").build();

            repository.save(driver);

            List<Driver> drivers = repository.findByColor(Color.RED.get());
            System.out.println(drivers);

        }
    }

    private App1() {
    }
}
