package org.jnosql.artemis.demo.se.document;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

@ApplicationScoped
public class MongoDBProducer {

    private static final String COLLECTION = "developers";

    public static final String MONGODB = "mongodb";

    private MongoDBDocumentConfiguration configuration;

    private MongoDBDocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new MongoDBDocumentConfiguration();
        managerFactory = configuration.get();
    }


    @Produces
    @Database(value = DatabaseType.DOCUMENT, provider = MONGODB)
    public DocumentCollectionManager getManager() {
        return managerFactory.get(COLLECTION);

    }

}
