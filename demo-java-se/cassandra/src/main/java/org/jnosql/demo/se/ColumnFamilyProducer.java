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

package org.jnosql.demo.se;


import jakarta.nosql.column.ColumnFamilyManager;
import org.eclipse.jnosql.communication.cassandra.column.CassandraColumnFamilyManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ColumnFamilyProducer {

    @Inject
    @ConfigProperty(name = "column")
    private ColumnFamilyManager columnManager;

    @Produces
    @ApplicationScoped
    public CassandraColumnFamilyManager getColumnManager() {
        return (CassandraColumnFamilyManager) columnManager;
    }

    public void close(@Disposes CassandraColumnFamilyManager manager) {
        manager.close();
    }

}
