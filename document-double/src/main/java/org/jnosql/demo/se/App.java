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
import org.eclipse.jnosql.mapping.DatabaseQualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jnosql.demo.se.MongoDBProducer.MONGODB;


public class App {

    private static final List<String> PHONES;

    static {
        PHONES = new ArrayList<>();
        PHONES.add("123456789");
        PHONES.add("234242");
    }

    private static final String ID = UUID.randomUUID().toString();
    private static final Person PERSON = Person.builder().
            withPhones(PHONES)
            .withName("Name")
            .withId(ID)
            .build();

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            System.out.println(" Using couchbase database");

            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            Person saved = template.insert(PERSON);
            System.out.println("Person saved" + saved);


            Optional<Person> person = template.select(Person.class)
                    .where("id").eq(ID).singleResult();
            System.out.println("Entity found: " + person);


            System.out.println(" Using mongodb database");

            template = container.select(DocumentTemplate.class)
                    .select(DatabaseQualifier.ofDocument(MONGODB)).get();

            saved = template.insert(PERSON);
            System.out.println("Person saved" + saved);


            person = template.select(Person.class)
                    .where("id").eq(ID).singleResult();
            System.out.println("Entity found: " + person);

        }
    }

    private App() {
    }
}
