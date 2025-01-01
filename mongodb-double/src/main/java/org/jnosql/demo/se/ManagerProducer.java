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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import org.eclipse.jnosql.communication.Settings;
import org.eclipse.jnosql.communication.semistructured.DatabaseConfiguration;
import org.eclipse.jnosql.communication.semistructured.DatabaseManager;
import org.eclipse.jnosql.communication.semistructured.DatabaseManagerFactory;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentConfiguration;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.DatabaseType;
import org.eclipse.jnosql.mapping.core.config.MicroProfileSettings;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.logging.Logger;

@ApplicationScoped
public class ManagerProducer {

    private static final Logger LOGGER = Logger.getLogger(ManagerProducer.class.getName());

    private static final String DATABASE = "jnosql.document.database.2";

    @ApplicationScoped
    @Produces
    @Database(provider = "romain", value = DatabaseType.DOCUMENT)
    public DatabaseManager getRomain() {
        LOGGER.info("Creating DocumentManager to romain database");
        Config config = ConfigProvider.getConfig();
        DatabaseConfiguration configuration = new MongoDBDocumentConfiguration();
        Settings settings = MicroProfileSettings.INSTANCE;
        DatabaseManagerFactory factory = configuration.apply(settings);
        return factory.apply(config.getValue(DATABASE, String.class));
    }

    public void close(@Disposes @Database(provider = "romain", value = DatabaseType.DOCUMENT) DatabaseManager manager) {
        LOGGER.info("Closing DocumentManager to romain database");
        manager.close();
    }
}
