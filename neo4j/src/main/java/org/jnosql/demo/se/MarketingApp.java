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
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.eclipse.jnosql.databases.tinkerpop.mapping.GraphTemplate;

import java.util.List;

import static org.apache.tinkerpop.gremlin.process.traversal.P.between;
import static org.apache.tinkerpop.gremlin.process.traversal.P.gte;
import static org.jnosql.demo.se.Person.builder;

public final class MarketingApp {


    private MarketingApp() {
    }


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate graph = container.select(GraphTemplate.class).get();
            Graph thinkerpop = container.select(Graph.class).get();

            graph.insert(builder().withAge(30L).withName("Banner")
                    .withOccupation("Developer").withSalary(3_000D).build());

            graph.insert(builder().withAge(32L).withName("Natalia")
                    .withOccupation("Developer").withSalary(5_000D).build());

            graph.insert(builder().withAge(40L).withName("Rose")
                    .withOccupation("Design").withSalary(1_000D).build());

            graph.insert(builder().withAge(22L).withName("tony")
                    .withOccupation("Developer").withSalary(4_500D).build());


            thinkerpop.tx().commit();

            Person banner = getPerson("Banner", graph);

            Person natalia = getPerson("Natalia", graph);

            Person rose = getPerson("Rose", graph);

            Person tony = getPerson("tony", graph);

            graph.edge(tony, "knows", rose).add("feel", "love");
            graph.edge(tony, "knows", natalia);

            graph.edge(natalia, "knows", rose);
            graph.edge(banner, "knows", rose);

            thinkerpop.tx().commit();

            List<Person> developers = graph.traversalVertex()
                    .has("occupation", "Developer")
                    .<Person>result().toList();

            List<Person> peopleWhoDeveloperKnows = graph.traversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .out("knows")
                    .<Person>result().toList();

            List<Person> both = graph.traversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .outE("knows")
                    .bothV()
                    .<Person>result()
                    .distinct()
                    .toList();

            List<Person> couple = graph.traversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .outE("knows")
                    .has("feel", "love")
                    .bothV()
                    .<Person>result()
                    .distinct()
                    .toList();

            System.out.println("Developers has salary greater than 3000 and age between 20 and 25: " + developers);
            System.out.println("Person who the Developers target know: " + peopleWhoDeveloperKnows);
            System.out.println("The person and the developers target: " + both);
            System.out.println("Developers to Valentine days: " + couple);

        }
    }

    private static Person getPerson(String name, GraphTemplate graph) {
        return graph.traversalVertex().hasLabel("Person")
                .has("name", name)
                .<Person>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }

}

