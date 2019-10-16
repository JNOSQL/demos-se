/*
 * Copyright (c) 2017 Otávio Santana and others
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

package org.jnosql.artemis.demo.se;


import org.eclipse.jnosql.artemis.DatabaseQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;

public class App2 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {



            Address address = new Address("10880 Malibu Point", "Malibu");
            Job job = new Job(1_000, "Rich");
            Person person = Person.builder().
                    withPhones(Arrays.asList("9999999", "55555"))
                    .withName("Tony stark")
                    .withAddress(address)
                    .withAge(50)
                    .withJob(job)
                    .build();

            PersonRepository repository = container.select(PersonRepository.class)
                    .select(DatabaseQualifier.ofDocument()).get();
            repository.save(person);

            List<Person> people = repository.findByName("Tony stark");
            System.out.println("Entity found: " + people);

        }
    }

    private App2() {
    }
}
