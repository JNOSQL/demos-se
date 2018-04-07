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

package org.jnosql.artemis.demo.se.mongodb;


import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.diana.api.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;

public class App {


    public static void main(String[] args) {

        Random random = new Random();
        Long id = random.nextLong();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Developer developer = Developer.builder().
                    withPhones(Arrays.asList("234", "432"))
                    .withName("Name")
                    .withId(id)
                    .build();
            DocumentTemplate documentTemplate = container.select(DocumentTemplate.class).get();
            Developer saved = documentTemplate.insert(developer);
            System.out.println("Developer saved" + saved);


            DocumentQuery query = select().from("Developer")
                    .where("_id").eq(id).build();

            Optional<Developer> personOptional = documentTemplate.singleResult(query);
            System.out.println("Entity found: " + personOptional);

        }
    }

    private App() {
    }
}
