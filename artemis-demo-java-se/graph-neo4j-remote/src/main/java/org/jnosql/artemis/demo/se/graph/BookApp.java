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

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class BookApp {

    private BookApp() {
    }

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate graph = container.select(GraphTemplate.class).get();
            Graph thinkerpop = container.select(Graph.class).get();

            graph.insert(Category.of("Software"));
            graph.insert(Category.of("Romance"));

            graph.insert(Category.of("Java"));
            graph.insert(Category.of("NoSQL"));
            graph.insert(Category.of("Micro Service"));

            graph.insert(Book.of("Effective Java"));
            graph.insert(Book.of("NoSQL Distilled"));
            graph.insert(Book.of("Migrating to Microservice Databases"));
            graph.insert(Book.of("The Shack"));

            thinkerpop.tx().commit();

            Category software = getCategory("Software", graph);
            Category romance = getCategory("Romance", graph);

            Category java = getCategory("Java", graph);
            Category nosql = getCategory("NoSQL", graph);
            Category microService = getCategory("Micro Service", graph);

            Book effectiveJava = getBook("Effective Java", graph);
            Book nosqlDistilled = getBook("NoSQL Distilled", graph);
            Book migratingMicroservice = getBook("Migrating to Microservice Databases", graph);
            Book shack = getBook("The Shack", graph);



            graph.edge(java, "is", software);
            graph.edge(nosql, "is", software);
            graph.edge(microService, "is", software);

            graph.edge(effectiveJava, "is", software);
            graph.edge(nosqlDistilled, "is", software);
            graph.edge(migratingMicroservice, "is", software);

            graph.edge(effectiveJava, "is", java);
            graph.edge(nosqlDistilled, "is", nosql);
            graph.edge(migratingMicroservice, "is", microService);


            graph.edge(shack, "is", romance);

            thinkerpop.tx().commit();

            List<String> softwareCategories = graph.getTraversalVertex().hasLabel("Category")
                    .has("name", "Software")
                    .in("is").hasLabel("Category").<Category>stream()
                    .map(Category::getName)
                    .collect(toList());

            List<String> softwareBooks = graph.getTraversalVertex().hasLabel("Category")
                    .has("name", "Software")
                    .in("is").hasLabel("Book").<Book>stream()
                    .map(Book::getName)
                    .collect(toList());

            List<String> sofwareNoSQLBooks = graph.getTraversalVertex().hasLabel("Category")
                    .has("name", "Software")
                    .in("is")
                    .has("name", "NoSQL")
                    .in("is").<Book>stream()
                    .map(Book::getName)
                    .collect(toList());


            System.out.println("The software categories: " + softwareCategories);
            System.out.println("The software books: " + softwareBooks);
            System.out.println("The software and NoSQL books: " + sofwareNoSQLBooks);


        }
    }

    private static Category getCategory(String name, GraphTemplate graph) {
        return graph.getTraversalVertex().hasLabel("Category")
                .has("name", name)
                .<Category>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }

    private static Book getBook(String name, GraphTemplate graph) {
        return graph.getTraversalVertex().hasLabel("Book")
                .has("name", name)
                .<Book>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }
}
