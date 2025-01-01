/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package org.jnosql.demo.se;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

import java.util.Arrays;
import java.util.List;

public class App {


    public static void main(String[] args) {

        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            var baggageRepository = container.select(BaggageRepository.class, DatabaseQualifier.ofDocument()).get();


            System.out.println("=".repeat(50));
            System.out.println("baggageRepository.register()");
            var baggage01 = baggageRepository.register(Baggage.create(faker));
            System.out.println("Baggage " + baggage01.ticket());
            System.out.println("Baggage items: ");
            Arrays.stream(baggage01.items()).forEach(System.out::println);

            System.out.println("=".repeat(50));
            System.out.println("baggageRepository.findByTicket()");
            Baggage registeredBaggage = baggageRepository.findByTicket(baggage01.ticket());

            System.out.println("Baggage " + registeredBaggage.ticket());
            System.out.println("Baggage items: ");
            Arrays.stream(registeredBaggage.items()).forEach(System.out::println);
            System.out.println("-".repeat(50));

            baggageRepository.registerAll(Baggage.create(faker), Baggage.create(faker));

            System.out.println("=".repeat(50));
            System.out.println("baggageRepository.findAll()");
            List<Baggage> baggageList = baggageRepository.findAll();
            System.out.println("Baggage List: " + baggageList.size());
            System.out.println("-".repeat(50));
            for (var baggage : baggageList) {
                System.out.println("Baggage " + baggage.ticket());
                System.out.println("Baggage items: ");
                Arrays.stream(registeredBaggage.items()).forEach(System.out::println);
                System.out.println("-".repeat(50));
            }
            System.out.println("=".repeat(50));
            System.out.println("baggageRepository.unregisterAll()");
            baggageRepository.unregisterAll(baggageList);

            System.out.println("=".repeat(50));
            System.out.println("baggageRepository.findAll()");
            List<Baggage> actualBaggageList = baggageRepository.findAll();
            System.out.println("Baggage List: " + actualBaggageList.size());

        }
    }
}
