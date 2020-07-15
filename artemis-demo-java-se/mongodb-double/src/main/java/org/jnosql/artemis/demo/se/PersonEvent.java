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

package org.jnosql.artemis.demo.se;


import jakarta.nosql.mapping.EntityPostPersit;
import jakarta.nosql.mapping.EntityPrePersist;
import jakarta.nosql.mapping.document.DocumentEntityPostPersist;
import jakarta.nosql.mapping.document.DocumentEntityPrePersist;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class PersonEvent {

    private static final Logger LOGGER = Logger.getLogger(PersonEvent.class.getName());

    public void preEntity(@Observes EntityPrePersist event) {
        LOGGER.info("Event to pre persistence" + event.getValue());
    }

    public void postEntity(@Observes EntityPostPersit event) {
        LOGGER.info("Event to post persistence" + event.getValue());
    }

    public void preColumn(@Observes DocumentEntityPrePersist event) {
        LOGGER.info("Event to pre document entity" + event.getEntity());
    }

    public void postColumn(@Observes DocumentEntityPostPersist event) {
        LOGGER.info("Event to post document entity" + event.getEntity());
    }
}
