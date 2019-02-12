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
package org.jnosql.artemis.demo.se.arangodb;

import org.jnosql.artemis.ConfigurationUnit;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.arangodb.document.ArangoDBDocumentCollectionManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ArangoDBProducer {

    private static final String HEROES = "heroes";

    @Inject
    @ConfigurationUnit(name = "document", database = HEROES)
    private DocumentCollectionManager documentManager;

    @Inject
    @ConfigurationUnit(name = "key-value", database = HEROES)
    private BucketManager bucketManager;



    @Produces
    public ArangoDBDocumentCollectionManager getCollectionManager() {
        return (ArangoDBDocumentCollectionManager) documentManager;
    }

    @Produces
    public BucketManager getBucketManager() {
        return bucketManager;
    }

}
