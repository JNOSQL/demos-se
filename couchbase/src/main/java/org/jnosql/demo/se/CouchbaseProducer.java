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

import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.keyvalue.BucketManager;
import jakarta.nosql.keyvalue.BucketManagerFactory;
import org.eclipse.jnosql.communication.couchbase.document.CouchbaseDocumentCollectionManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class CouchbaseProducer {

    private static final String HEROES = "heroes";

    @Inject
    @ConfigProperty(name = "document")
    private DocumentCollectionManagerFactory entityManager;

    @Inject
    @ConfigProperty(name = "keyvalue")
    private BucketManagerFactory bucketManager;

    @Produces
    public CouchbaseDocumentCollectionManager getCouchbaseDocumentCollectionManager() {
        return entityManager.get(HEROES);
    }

    @Produces
    public List<String> getHeroList() {
        return bucketManager.getList(HEROES, String.class);
    }

    @Produces
    public Set<String> getHeroSet() {
        return bucketManager.getSet(HEROES, String.class);
    }

    @Produces
    public BucketManager getBucketManager() {
        return bucketManager.getBucketManager(HEROES);
    }

}
