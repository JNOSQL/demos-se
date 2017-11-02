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

package org.jnosql.artemis.demo.se.document;


import org.jnosql.artemis.DatabaseQualifier;
import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jnosql.artemis.demo.se.document.CouchbaseProducer.COUCHBASE;
import static org.jnosql.artemis.demo.se.document.MongoDBProducer.MONGODB;
import static org.jnosql.diana.api.document.DocumentCondition.eq;
import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;


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

            DocumentTemplate documentTemplate = container.select(DocumentTemplate.class)
                    .select(DatabaseQualifier.ofDocument(COUCHBASE)).get();

            Person saved = documentTemplate.insert(PERSON);
            System.out.println("Person saved" + saved);

            DocumentQuery query = select().from("Person")
                    .where(eq(Document.of("_id", ID))).build();

            Optional<Person> person = documentTemplate.singleResult(query);
            System.out.println("Entity found: " + person);


            System.out.println(" Using mongodb database");

            documentTemplate = container.select(DocumentTemplate.class)
                    .select(DatabaseQualifier.ofDocument(MONGODB)).get();

            saved = documentTemplate.insert(PERSON);
            System.out.println("Person saved" + saved);


            person = documentTemplate.singleResult(query);
            System.out.println("Entity found: " + person);

        }
    }

    private App() {
    }
}
