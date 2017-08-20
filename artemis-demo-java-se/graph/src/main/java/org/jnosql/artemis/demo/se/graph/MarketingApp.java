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


import org.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.tinkerpop.gremlin.process.traversal.P.between;
import static org.apache.tinkerpop.gremlin.process.traversal.P.gte;
import static org.jnosql.artemis.demo.se.graph.Person.builder;

public class MarketingApp {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate graph = container.select(GraphTemplate.class).get();

            Person banner = graph.insert(builder().withAge(30).withName("Banner")
                    .withOccupation("Developer").withSalary(3_000D).build());

            Person natalia = graph.insert(builder().withAge(32).withName("Natalia")
                    .withOccupation("Developer").withSalary(5_000D).build());

            Person rose = graph.insert(builder().withAge(40).withName("Rose")
                    .withOccupation("Design").withSalary(1_000D).build());

            Person tony = graph.insert(builder().withAge(22).withName("tony")
                    .withOccupation("Developer").withSalary(4_500D).build());


            graph.edge(tony, "knows", rose).add("feel", "love");
            graph.edge(tony, "knows", natalia);

            graph.edge(natalia, "knows", rose);
            graph.edge(banner, "knows", rose);

            List<Person> developers = graph.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .<Person>stream().collect(toList());

            List<Person> peopleWhoDeveloperKnows = graph.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .out("knows")
                    .<Person>stream().collect(toList());

            List<Person> both = graph.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .outE("knows")
                    .bothV()
                    .<Person>stream()
                    .distinct()
                    .collect(toList());

            List<Person> couple = graph.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .outE("knows")
                    .has("feel", "love")
                    .bothV()
                    .<Person>stream()
                    .distinct()
                    .collect(toList());

            System.out.println("Developers has salary greater than 3000 and age between 20 and 25: " + developers);
            System.out.println("Person who the Developers target know: " + peopleWhoDeveloperKnows);
            System.out.println("The person and the developers target: " + both);
            System.out.println("Developers to Valentine days: " + couple);

        }
    }

    private MarketingApp() {
    }
}

