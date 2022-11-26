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

import jakarta.nosql.Settings;
import jakarta.nosql.document.DocumentConfiguration;
import jakarta.nosql.document.DocumentManager;
import jakarta.nosql.document.DocumentManagerFactory;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import org.eclipse.jnosql.communication.mongodb.document.MongoDBDocumentConfiguration;
import org.eclipse.jnosql.mapping.config.MicroProfileSettings;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ManagerProducer {

    private static final String DATABASE = "jnosql.document.database.2";

    @ApplicationScoped
    @Produces
    @Database(provider = "romain", value = DatabaseType.DOCUMENT)
    public DocumentManager getRomain() {
        Config config = ConfigProvider.getConfig();
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        Settings settings = MicroProfileSettings.INSTANCE;
        DocumentManagerFactory factory = configuration.apply(settings);
        DocumentManager manager = factory.apply(config.getValue(DATABASE, String.class));
        return manager;
    }

}
