package org.jnosql.artemis.demo.se.column;


import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.jnosql.artemis.EntityPostPersit;
import org.jnosql.artemis.column.ColumnEntityPostPersist;
import org.jnosql.artemis.column.ColumnEntityPrePersist;

@ApplicationScoped
public class PersonEvent {

    private static final Logger LOGGER = Logger.getLogger(PersonEvent.class.getName());

    public void preEntity(@Observes EntityPostPersit event) {
        LOGGER.info("Event to pre persistence" + event.getValue());
    }

    public void postEntity(@Observes EntityPostPersit event) {
        LOGGER.info("Event to post persistence" + event.getValue());
    }

    public void preColumn(@Observes ColumnEntityPrePersist event) {
        LOGGER.info("Event to pre Column entity" + event.getEntity());
    }

    public void postColumn(@Observes ColumnEntityPostPersist event) {
        LOGGER.info("Event to post column entity" + event.getEntity());
    }
}
