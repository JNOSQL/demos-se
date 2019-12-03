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
package org.jnosql.artemis.demo.se;

import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.eclipse.jnosql.artemis.graph.GraphTraversalSourceSupplier;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.io.File;

@ApplicationScoped
public class GraphProducer {

    private Graph graph;


    @PostConstruct
    public void init() {
        String absolutePath = new File("").getAbsolutePath() + "/target/jnosql/";
        this.graph = Neo4jGraph.open(absolutePath);
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
