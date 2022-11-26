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

import jakarta.nosql.document.DocumentCondition;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jakarta.nosql.Sort.asc;
import static jakarta.nosql.document.DocumentCondition.and;
import static jakarta.nosql.document.DocumentCondition.eq;
import static jakarta.nosql.document.DocumentCondition.gte;
import static jakarta.nosql.document.DocumentCondition.or;

public class App10 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

           Worker poliana = Worker.builder()
                   .age(30).name("Poliana")
                   .city("Salvador")
                   .gender(Gender.FEMALE)
                   .dailyHours(30).build();

            Worker otavio = Worker.builder()
                    .age(35).name("Otavio")
                    .city("Salvador")
                    .gender(Gender.MALE)
                    .dailyHours(30).build();

            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.insert(Arrays.asList(otavio, poliana));

            DocumentCondition maleAfterThirty = and(eq("gender", Gender.MALE),
                    gte("age", 30));

            DocumentCondition femaleAfterThirty = and(eq("gender", Gender.FEMALE),
                    gte("age", 30));

            final DocumentQuery query =  DocumentQuery.builder()
                    .from("Worker")
                    .sort(asc("name"))
                    .where(or(maleAfterThirty, femaleAfterThirty))
                    .build();

            Stream<Worker> stream = template.select(query);
            List<Worker> workers = stream.collect(Collectors.toList());
            workers.forEach(System.out::println);

            template.delete(Worker.class, otavio.getId());
            template.delete(Worker.class, poliana.getId());
        }
    }

    private App10() {
    }
}
