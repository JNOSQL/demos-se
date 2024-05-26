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
import org.eclipse.jnosql.mapping.document.DocumentTemplate;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.eclipse.jnosql.communication.semistructured.SelectQuery.select;


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

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Developer saved = template.insert(developer);
            System.out.println("Developer saved" + saved);


            var query = select().from("developer")
                    .where("_id").eq(id).build();

            Optional<Developer> optional = template.select(Developer.class).where("id")
                    .eq(id).singleResult();
            System.out.println("Entity found: " + optional);

        }
    }

    private App() {
    }
}
