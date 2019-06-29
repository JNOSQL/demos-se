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


import jakarta.nosql.mapping.document.DocumentTemplate;
import jakarta.nosql.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static jakarta.nosql.document.DocumentQuery.select;

public class App3 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Random random = new Random();
            long id = random.nextLong();
            Person person = Person.builder().
                    withPhones(Arrays.asList("234", "432"))
                    .withName("Name")
                    .withId(id)
                    .withAddress(new Address("Engenheiro Jose Anasoh", "Salvador"))
                    .build();

            DocumentTemplate repository = container.select(DocumentTemplate.class).get();
            Person saved = repository.insert(person);
            System.out.println("Person saved" + saved);

            DocumentQuery query = select().from("Person")
                    .where("_id").eq(id).build();

            List<Person> people = repository.select(query);
            System.out.println("Entity found: " + people);

        }
    }

    private App3() {
    }
}
