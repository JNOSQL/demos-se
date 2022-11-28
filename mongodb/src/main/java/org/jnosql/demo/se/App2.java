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


import org.eclipse.jnosql.mapping.DatabaseQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App2 {


    public static void main(String[] args) {

        Random random = new Random();
        long id = random.nextLong();

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Person person = Person.builder().
                    withPhones(Arrays.asList("234", "432"))
                    .withName("Name")
                    .withId(id)
                    .build();

            PersonRepository repository = container.select(PersonRepository.class)
                    .select(DatabaseQualifier.ofDocument()).get();
            repository.save(person);

            List<Person> people = repository.findByName("Name");
            System.out.println("Entity found: " + people);
            //repository.findByPhones("234").forEach(System.out::println);

        }
    }

    private App2() {
    }
}
