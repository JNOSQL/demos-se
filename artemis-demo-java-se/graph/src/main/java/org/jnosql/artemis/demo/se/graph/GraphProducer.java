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

import com.thinkaurelius.titan.core.TitanFactory;
import org.apache.tinkerpop.gremlin.structure.Graph;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class GraphProducer {

    private static final String DATA_DIR = "./target/jnosql-graph";

    private Graph graph = TitanFactory.open("berkeleyje:" + DATA_DIR);

    @Produces
    @ApplicationScoped
    public Graph getGraph() {
        return graph;
    }

}
