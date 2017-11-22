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

package org.jnosql.artemis.demo.se.graph;


import org.jnosql.artemis.DatabaseQualifier;
import org.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.tinkerpop.gremlin.process.traversal.P.between;
import static org.apache.tinkerpop.gremlin.process.traversal.P.gte;
import static org.jnosql.artemis.demo.se.graph.Person.builder;

public final class MarketingApp2 {


    private MarketingApp2() {
    }


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            PersonRepository repository = container.select(PersonRepository.class, DatabaseQualifier.ofGraph()).get();

            Person banner = repository.save(builder().withAge(30).withName("Banner")
                    .withOccupation("Developer").withSalary(3_000D).build());

            Person natalia = repository.save(builder().withAge(32).withName("Natalia")
                    .withOccupation("Developer").withSalary(5_000D).build());

            Person rose = repository.save(builder().withAge(40).withName("Rose")
                    .withOccupation("Design").withSalary(1_000D).build());

            Person tony = repository.save(builder().withAge(22).withName("tony")
                    .withOccupation("Developer").withSalary(4_500D).build());


            System.out.println("findByOccupationAndSalaryGreaterThan");
            repository.findByOccupationAndSalaryGreaterThan("Developer", 3_000D)
                    .forEach(System.out::println);
            System.out.println("findByAgeBetween");
            repository.findByAgeBetween(20, 30)
                    .forEach(System.out::println);


        }
    }

}

