/*
 *  Copyright (c) 2017 Otávio Santana and others
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
package org.jnosql.demo.se;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.example.GraphOfTheGodsFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GraphProducer {

    private static final String FILE_CONF = "conf/janusgraph-berkeleyje-lucene.properties";

    private Graph graph;


    @PostConstruct
    public void init() {
        JanusGraph janusGraph = JanusGraphFactory.open(FILE_CONF);
        GraphTraversalSource g = janusGraph.traversal();
        if (g.V().count().next() == 0) {
            GraphOfTheGodsFactory.load(janusGraph);
        }
        this.graph = janusGraph;
    }

    @Produces
    @ApplicationScoped
    public Graph getGraph() {
        return graph;
    }

    public void close(@Disposes Graph graph) throws Exception {
        graph.close();
    }
}
