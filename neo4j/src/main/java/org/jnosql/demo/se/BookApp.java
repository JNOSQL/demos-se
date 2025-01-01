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

            List<String> softwareCategories = graph.traversalVertex().hasLabel("Category")
                    .has("name", "Software")
                    .in("is").hasLabel("Category").<Category>result()
                    .map(Category::getName)
                    .toList();

            List<String> softwareBooks = graph.traversalVertex().hasLabel("Category")
                    .has("name", "Software")
                    .in("is").hasLabel("Book").<Book>result()
                    .map(Book::getName)
                    .toList();

            List<String> sofwareNoSQLBooks = graph.traversalVertex().hasLabel("Category")
                    .has("name", "Software")
                    .in("is")
                    .has("name", "NoSQL")
                    .in("is").<Book>result()
                    .map(Book::getName)
                    .toList();


            System.out.println("The software categories: " + softwareCategories);
            System.out.println("The software books: " + softwareBooks);
            System.out.println("The software and NoSQL books: " + sofwareNoSQLBooks);


        }
    }

    private static Category getCategory(String name, GraphTemplate graph) {
        return graph.traversalVertex().hasLabel("Category")
                .has("name", name)
                .<Category>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }

    private static Book getBook(String name, GraphTemplate graph) {
        return graph.traversalVertex().hasLabel("Book")
                .has("name", name)
                .<Book>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }
}
