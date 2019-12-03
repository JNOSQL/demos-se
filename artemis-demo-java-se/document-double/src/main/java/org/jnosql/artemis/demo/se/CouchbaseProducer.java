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


import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import jakarta.nosql.document.DocumentCollectionManager;
import org.eclipse.jnosql.diana.couchbase.document.CouchbaseDocumentConfiguration;
import org.eclipse.jnosql.diana.couchbase.document.CouhbaseDocumentCollectionManagerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CouchbaseProducer {

    private static final String COLLECTION = "default";

    public static final String COUCHBASE = "couchbase";

    private CouchbaseDocumentConfiguration configuration;

    private CouhbaseDocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new CouchbaseDocumentConfiguration();
        managerFactory = configuration.get();
    }


    @Produces
    @Database(value = DatabaseType.DOCUMENT, provider = COUCHBASE)
    public DocumentCollectionManager getManager() {
        return managerFactory.get(COLLECTION);

    }
}
