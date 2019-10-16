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

package org.jnosql.artemis.demo.se;


import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import org.eclipse.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MongoDBProducer {

    private static final String COLLECTION = "developers";

    public static final String MONGODB = "mongodb";

    private MongoDBDocumentConfiguration configuration;

    private DocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new MongoDBDocumentConfiguration();
        managerFactory = configuration.get();
    }

    @Produces
    @Database(value = DatabaseType.DOCUMENT, provider = MONGODB)
    public DocumentCollectionManager getManager() {
        return managerFactory.get(COLLECTION);

    }

}
