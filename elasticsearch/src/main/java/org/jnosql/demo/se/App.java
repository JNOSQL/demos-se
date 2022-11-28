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


import jakarta.nosql.mapping.document.DocumentTemplate;
import jakarta.nosql.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static jakarta.nosql.document.DocumentQuery.select;

public class App {


    public static void main(String[] args) {

        Random random = new Random();
        long id = random.nextLong();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Address address = Address.builder()
                    .withCity("Salvador")
                    .withStreet("Rua Engenheiro Jose")
                    .withNumber(10).build();

            Developer developer = Developer.builder().
                    withPhones(Arrays.asList("85 85 343435684", "55 11 123448684"))
                    .withName("Poliana Lovelace")
                    .withId(id)
                    .withAddress(address)
                    .build();

            DocumentTemplate documentTemplate = container.select(DocumentTemplate.class).get();
            Developer saved = documentTemplate.insert(developer);
            System.out.println("Developer saved" + saved);


            DocumentQuery query = select().from("developer")
                    .where("_id").eq(id).build();

            Optional<Developer> personOptional = documentTemplate.singleResult(query);
            System.out.println("Entity found: " + personOptional);

        }
    }

    private App() {
    }
}
