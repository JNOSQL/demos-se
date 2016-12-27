package org.jnosql.artemis.demo.se.key;


import org.jnosql.artemis.EntityPostPersit;
import org.jnosql.artemis.key.KeyValueEntityPostPersist;
import org.jnosql.artemis.key.KeyValueEntityPrePersist;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class UserEvent {

    private static final Logger LOGGER = Logger.getLogger(UserEvent.class.getName());

    public void preEntity(@Observes EntityPostPersit event) {
        LOGGER.info("Event to pre persistence" + event.getValue());
    }

    public void postEntity(@Observes EntityPostPersit event) {
        LOGGER.info("Event to post persistence" + event.getValue());
    }

    public void preColumn(@Observes KeyValueEntityPrePersist event) {
        LOGGER.info("Event to pre key entity" + event.getEntity());
    }

    public void postColumn(@Observes KeyValueEntityPostPersist event) {
        LOGGER.info("Event to post key entity" + event.getEntity());
    }
}
