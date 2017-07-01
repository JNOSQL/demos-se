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


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

import java.util.Arrays;
import java.util.Optional;

public class App {

    private static final Person PERSON = Person.builder().
            withPhones(Arrays.asList("234", "432"))
            .withName("Name")
            .withId(1)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer weldContainer = weld.initialize()) {
            DocumentTemplate crudOperation = weldContainer.instance().select(DocumentTemplate.class).get();
            Person saved = crudOperation.insert(PERSON);
            System.out.println("Person saved" + saved);

            DocumentQuery query = DocumentQuery.of("Person");
            query.and(DocumentCondition.eq(Document.of("_id", 1L)));

            Optional<Person> person = crudOperation.singleResult(query);
            System.out.println("Entity found: " + person);

        }
    }
}
