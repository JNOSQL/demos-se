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

package org.jnosql.artemis.demo.se.document;


import org.jnosql.artemis.document.DocumentRepositoryProducer;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentCollectionManager;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentConfiguration;
import org.jnosql.diana.couchbase.document.CouhbaseDocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManager;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.inject.Inject;
import java.util.Objects;

public class RepositoryProducer {

    public static final String DATABASE = "default";

    @Inject
    private DocumentRepositoryProducer producer;

    private MongoDBDocumentCollectionManager mongoDBCollectionManager;

    private CouchbaseDocumentCollectionManager couchBaseCollectionManager;

    private CouchbaseDocumentCollectionManager getCouchbaseManager() {
        if (Objects.isNull(couchBaseCollectionManager)) {
            CouchbaseDocumentConfiguration configuration = new CouchbaseDocumentConfiguration();
            CouhbaseDocumentCollectionManagerFactory factory = configuration.get();
            couchBaseCollectionManager = factory.get(DATABASE);
        }
        return couchBaseCollectionManager;
    }

    private MongoDBDocumentCollectionManager getMongoManager() {
        if (Objects.isNull(mongoDBCollectionManager)) {
            MongoDBDocumentConfiguration configuration = new MongoDBDocumentConfiguration();
            MongoDBDocumentCollectionManagerFactory mongoDBFactory = configuration.get();
            mongoDBCollectionManager = mongoDBFactory.get("default");
        }

        return mongoDBCollectionManager;
    }


}
