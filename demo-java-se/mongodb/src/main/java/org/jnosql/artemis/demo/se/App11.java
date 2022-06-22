/*
 * Copyright (c) 2022 Otávio Santana and others
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

import jakarta.nosql.document.DocumentDeleteQuery;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;

public class App11 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            PersonRepository repository = container.select(PersonRepository.class).get();
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            template.delete(DocumentDeleteQuery.delete().from("Person").build());
            Person otavio = Person.builder().withId(1L)
                    .withName("Otavio")
                    .withJob(new Job(10, "Java Developer"))
                    .build();

            Person poliana = Person.builder().withId(2L)
                    .withName("Poliana")
                    .withJob(new Job(10, "Java Developer"))
                    .build();

            Person elder = Person.builder().withId(3L)
                    .withName("elder")
                    .withJob(new Job(10, "Java Developer"))
                    .build();

            Person yanaga = Person.builder().withId(4L)
                    .withName("Yanaga")
                    .withJob(new Job(10, "Java Developer"))
                    .build();

            repository.save(Arrays.asList(otavio, poliana, elder, yanaga));

            List<Person> people = repository.findByJob_Occupation("Java Developer", Pagination.page(1).size(1));
            System.out.println("First pagination");
            people.forEach(System.out::println);
            people = repository.findByJob_Occupation("Java Developer", Pagination.page(2).size(1));
            System.out.println("second pagination");
            people.forEach(System.out::println);

        }
    }
}
