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
import org.eclipse.jnosql.mapping.graph.Edge;
import org.eclipse.jnosql.mapping.graph.GraphTemplate;

public final class BookApp {

    private BookApp() {
    }

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate graph = container.select(GraphTemplate.class).get();

            graph.insert(Category.of("Software"));
            graph.insert(Category.of("Romance"));

            graph.insert(Category.of("Java"));
            graph.insert(Category.of("NoSQL"));
            graph.insert(Category.of("Micro Service"));

            graph.insert(Book.of("Effective Java"));
            graph.insert(Book.of("NoSQL Distilled"));
            graph.insert(Book.of("Migrating to Microservice Databases"));
            graph.insert(Book.of("The Shack"));


            Category software = getCategory("Software", graph);
            Category romance = getCategory("Romance", graph);

            Category java = getCategory("Java", graph);
            Category nosql = getCategory("NoSQL", graph);
            Category microService = getCategory("Micro Service", graph);

            Book effectiveJava = getBook("Effective Java", graph);
            Book nosqlDistilled = getBook("NoSQL Distilled", graph);
            Book migratingMicroservice = getBook("Migrating to Microservice Databases", graph);
            Book shack = getBook("The Shack", graph);



            graph.edge(Edge.source(java).label("is").target(software).build());
            graph.edge(Edge.source(nosql).label("is").target(software).build());
            graph.edge(Edge.source(microService).label("is").target(software).build());

            graph.edge(Edge.source(effectiveJava).label("is").target(software).build());
            graph.edge(Edge.source(nosqlDistilled).label("is").target(software).build());
            graph.edge(Edge.source(migratingMicroservice).label("is").target(software).build());

            graph.edge(Edge.source(effectiveJava).label("is").target(java).build());
            graph.edge(Edge.source(nosqlDistilled).label("is").target(nosql).build());
            graph.edge(Edge.source(migratingMicroservice).label("is").target(microService).build());


            graph.edge(Edge.source(shack).label("is").target(romance).build());

        }
    }

    private static Category getCategory(String name, GraphTemplate template) {
        return template.select(Category.class).where("name").eq(name).<Category>singleResult()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }

    private static Book getBook(String name, GraphTemplate template) {
        return template.select(Book.class).where("name").eq(name).<Book>singleResult()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }
}
