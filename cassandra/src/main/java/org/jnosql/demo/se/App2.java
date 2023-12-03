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

import java.util.Arrays;
import java.util.Optional;

import static org.eclipse.jnosql.mapping.DatabaseQualifier.ofColumn;

public class App2 {

    private static final Person PERSON = Person.builder()
            .id(1)
            .phones(Arrays.asList("234", "432"))
            .name("Name")
            .ignore("Just Ignore")
            .build();
    public static void main(String[] args) {

        try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            PersonRepository repository = container.select(PersonRepository.class).select(ofColumn()).get();
            Person saved = repository.save(PERSON);
            System.out.println("Person saved" + saved);

            Optional<Person> person = repository.findById(1L);
            System.out.println("Entity found: " + person);

        }
    }


    private App2() {}
}
