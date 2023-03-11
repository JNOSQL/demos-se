/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package org.jnosql.demo.se;


import org.eclipse.jnosql.communication.Settings;
import org.eclipse.jnosql.communication.document.DocumentConfiguration;
import org.eclipse.jnosql.communication.document.DocumentManager;
import org.eclipse.jnosql.communication.document.DocumentManagerFactory;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.DatabaseType;
import org.eclipse.jnosql.communication.mongodb.document.MongoDBDocumentConfiguration;
import org.eclipse.jnosql.mapping.config.MappingConfigurations;
import org.eclipse.jnosql.mapping.config.MicroProfileSettings;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MongoDBProducer {

    public static final String MONGODB = "mongodb";

    @Produces
    @Database(value = DatabaseType.DOCUMENT, provider = MONGODB)
    @ApplicationScoped
    public DocumentManager getManager() {
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        Settings settings = MicroProfileSettings.INSTANCE;
        DocumentManagerFactory factory = configuration.apply(settings);
        String database = settings.get(MappingConfigurations.DOCUMENT_DATABASE,
                String.class).orElseThrow();
        return factory.apply(database);
    }
    public void close(@Disposes @Database(value = DatabaseType.DOCUMENT, provider = MONGODB) DocumentManager manager) {
        manager.close();
    }

}
