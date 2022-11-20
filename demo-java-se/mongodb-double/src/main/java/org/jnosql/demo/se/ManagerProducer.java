/*
 * Copyright (c) 2020 Otávio Santana and others
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

import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ManagerProducer {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentCollectionManager greek;


    @Inject
    @ConfigProperty(name = "db2")
    private DocumentCollectionManager romain;

    @ApplicationScoped
    @Produces
    @Database(provider = "greek", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getGreek() {
        return greek;
    }

    @ApplicationScoped
    @Produces
    @Database(provider = "romain", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getRomain() {
        return romain;
    }

    public void close(@Disposes @Any DocumentCollectionManager manager) {
        manager.close();
    }
}
