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

package org.jnosql.demo.se.infra;


import jakarta.nosql.document.DocumentCollectionManager;
import org.eclipse.jnosql.communication.mongodb.document.MongoDBDocumentCollectionManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class MongoDBProducer {


    @Inject
    @ConfigProperty(name = "document")
    private DocumentCollectionManager manager;

    @Produces
    public MongoDBDocumentCollectionManager getManager() {
        return (MongoDBDocumentCollectionManager) manager;
    }

    public void destroy(@Disposes DocumentCollectionManager manager) {
        manager.close();
    }

}
