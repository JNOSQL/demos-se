package org.jnosql.artemis.demo.se;

import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ManagerProducer {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentCollectionManager greek;


    @Inject
    @ConfigProperty(name = "db2")
    private DocumentCollectionManager romain;

    @ApplicationScoped
    @Produces
    @Database(provider = "greek", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getGreek() {
        return greek;
    }

    @ApplicationScoped
    @Produces
    @Database(provider = "romain", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getRomain() {
        return romain;
    }
}
