package org.jnosql.artemis.demo.se;

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
