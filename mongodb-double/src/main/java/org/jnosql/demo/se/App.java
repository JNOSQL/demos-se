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

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GreekService greek = container.select(GreekService.class).get();
            RomanService romain = container.select(RomanService.class).get();
            God artemis = new God("Artemis", Arrays.asList("Moon", "Hunt"));
            God diana = new God("Diana", Arrays.asList("Moon", "Hunt"));
            romain.insert(diana);
            greek.insert(artemis);

            LOGGER.info("Finding in the Greek service ");
            LOGGER.info("Finding by Artemis : " + greek.findName("Artemis").toList());
            LOGGER.info("Finding by Diana " + greek.findName("Diana").toList());

            LOGGER.info("Finding in the Roman service ");
            LOGGER.info("Finding by Artemis : " + romain.findName("Artemis").toList());
            LOGGER.info("Finding by Diana " + romain.findName("Diana").toList());

        }
    }

    private App() {
    }
}
