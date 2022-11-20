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

import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.keyvalue.BucketManager;
import org.eclipse.jnosql.communication.arangodb.document.ArangoDBDocumentCollectionManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ArangoDBProducer {

    @Inject
    @ConfigProperty(name = "document")
    private DocumentCollectionManager documentManager;

    @Inject
    @ConfigProperty(name = "keyvalue")
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
