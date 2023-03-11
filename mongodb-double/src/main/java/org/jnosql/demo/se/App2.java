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


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.logging.Logger;

public class App2 {

    private static final Logger LOGGER = Logger.getLogger(App2.class.getName());

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Service service = container.select(Service.class).get();
            GodRepository greek = service.getGreek();
            GodRepository romain = service.getRomain();
            God artemis = new God("Artemis", Arrays.asList("Moon", "Hunt"));
            God diana = new God("Diana", Arrays.asList("Moon", "Hunt"));
            romain.save(diana);
            greek.save(artemis);

            LOGGER.info("Finding in the Greek service ");
            LOGGER.info("Finding by Artemis : " + greek.findByName("Artemis"));
            LOGGER.info("Finding by Diana " + greek.findByName("Diana"));

            LOGGER.info("Finding in the Roman service ");
            LOGGER.info("Finding by Artemis : " + romain.findByName("Artemis"));
            LOGGER.info("Finding by Diana " + romain.findByName("Diana"));

        }
    }

    private App2() {
    }
}
