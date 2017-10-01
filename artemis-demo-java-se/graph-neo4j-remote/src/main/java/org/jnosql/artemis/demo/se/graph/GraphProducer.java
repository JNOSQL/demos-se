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
package org.jnosql.artemis.demo.se.graph;

import com.steelbridgelabs.oss.neo4j.structure.Neo4JElementIdProvider;
import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.ConfigurationUnit;
import org.neo4j.driver.v1.Driver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class GraphProducer {


    private Neo4JGraph graph;

    @Inject
    @ConfigurationUnit
    private Driver driver;


    @PostConstruct
    public void init() {
        Neo4JElementIdProvider<?> vertexIdProvider = new Neo4JNativeElementIdProvider();
        Neo4JElementIdProvider<?> edgeIdProvider = new Neo4JNativeElementIdProvider();
        this.graph = new Neo4JGraph(driver, vertexIdProvider, edgeIdProvider);
        graph.setProfilerEnabled(true);
    }

    @Produces
    @ApplicationScoped
    public Graph getGraph() {
        return graph;
    }

    public void close(@Disposes Graph graph) throws Exception {
        graph.close();
        driver.close();
    }
}
