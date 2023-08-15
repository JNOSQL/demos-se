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
import jakarta.nosql.document.DocumentTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Person saved = template.insert(person);
            System.out.println("Person saved" + saved);

            List<Person> people = template.<Person>select(Person.class)
                    .where("id").eq(id)
                    .result();
            System.out.println("Entity found: " + people);

        }
    }

    private App3() {
    }
}