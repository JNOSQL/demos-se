/*
 *  Copyright (c) 2019 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.jnosql.artemis.demo.se;


import com.arangodb.tinkerpop.gremlin.utils.ArangoDBConfigurationBuilder;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.eclipse.jnosql.artemis.graph.GraphTraversalSourceSupplier;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GraphProducer {

    private Graph graph;


    @PostConstruct
    public void init() {
        ArangoDBConfigurationBuilder builder = new ArangoDBConfigurationBuilder();
        builder.graph("marketing")
                .withVertexCollection("Person")
                .withEdgeCollection("knows")
                .configureEdge("knows", "Person", "Person");
        BaseConfiguration conf = builder.build();
        this.graph = GraphFactory.open(conf);
    }

    @Produces
    @ApplicationScoped
    public Graph getGraph() {
        return graph;
    }

    @Produces
    @ApplicationScoped
    public GraphTraversalSourceSupplier getSupplier() {
        return () -> graph.traversal();
    }

    public void close(@Disposes Graph graph) throws Exception {
        graph.close();
    }

}
