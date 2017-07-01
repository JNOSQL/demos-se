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

package org.jnosql.artemis.demo.se.column;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.cassandra.column.CassandraColumnFamilyManager;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;
import org.jnosql.diana.cassandra.column.CassandraDocumentEntityManagerFactory;

@ApplicationScoped
public class CassandraProducer {

    private static final String KEY_SPACE = "developers";

    private CassandraConfiguration cassandraConfiguration;

    private CassandraDocumentEntityManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        cassandraConfiguration = new CassandraConfiguration();
        managerFactory = cassandraConfiguration.get();
    }


    @Produces
    public ColumnFamilyManager getManager() {
        return managerFactory.get(KEY_SPACE);
    }

    @Produces
    public CassandraColumnFamilyManager getManagerCassandra() {
        return managerFactory.get(KEY_SPACE);
    }

}
