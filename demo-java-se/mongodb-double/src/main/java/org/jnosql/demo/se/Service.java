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

import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Service {

    @Inject
    @Database(provider = "greek", value = DatabaseType.DOCUMENT)
    private GodRepository greek;


    @Inject
    @Database(provider = "romain", value = DatabaseType.DOCUMENT)
    private GodRepository romain;

    public GodRepository getGreek() {
        return greek;
    }

    public GodRepository getRomain() {
        return romain;
    }
}
