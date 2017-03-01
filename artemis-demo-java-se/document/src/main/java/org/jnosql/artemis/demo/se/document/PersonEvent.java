package org.jnosql.artemis.demo.se.document;


import org.jnosql.artemis.EntityPostPersit;
import org.jnosql.artemis.EntityPrePersist;
import org.jnosql.artemis.document.DocumentEntityPostPersist;
import org.jnosql.artemis.document.DocumentEntityPrePersist;

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
