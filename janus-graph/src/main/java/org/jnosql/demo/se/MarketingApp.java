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
import org.eclipse.jnosql.databases.tinkerpop.mapping.GraphTemplate;

import java.util.List;

import static org.apache.tinkerpop.gremlin.process.traversal.P.between;
import static org.apache.tinkerpop.gremlin.process.traversal.P.gte;

public final class MarketingApp {


    private MarketingApp() {
    }


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate template = container.select(GraphTemplate.class).get();

            Person banner = template.insert(Person.builder().withAge(30).withName("Banner")
                    .withOccupation("Developer").withSalary(3_000D).build());

            Person natalia = template.insert(Person.builder().withAge(32).withName("Natalia")
                    .withOccupation("Developer").withSalary(5_000D).build());

            Person rose = template.insert(Person.builder().withAge(40).withName("Rose")
                    .withOccupation("Design").withSalary(1_000D).build());

            Person tony = template.insert(Person.builder().withAge(22).withName("tony")
                    .withOccupation("Developer").withSalary(4_500D).build());


            template.edge(tony, "knows", rose).add("feel", "love");
            template.edge(tony, "knows", natalia);

            template.edge(natalia, "knows", rose);
            template.edge(banner, "knows", rose);

            List<Person> developers = template.traversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .<Person>result().toList();

            List<Person> peopleWhoDeveloperKnows = template.traversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .out("knows")
                    .<Person>result().toList();

            List<Person> both = template.traversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(20, 25))
                    .has("occupation", "Developer")
                    .outE("knows")
                    .bothV()
                    .<Person>result()
                    .distinct()
                    .toList();

            List<Person> couple = template.traversalVertex()
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

}

