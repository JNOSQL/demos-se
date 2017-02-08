package org.jnosql.artemis.demo.se.document;


import org.jnosql.artemis.document.DocumentRepository;
import org.jnosql.artemis.document.DocumentRepositoryProducer;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentCollectionManager;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentConfiguration;
import org.jnosql.diana.couchbase.document.CouhbaseDocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManager;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Objects;

public class RepositoryProducer {

    public static final String DATABASE = "default";
    @Inject
    private DocumentRepositoryProducer producer;

    private MongoDBDocumentCollectionManager mongoDBCollectionManager;

    private CouchbaseDocumentCollectionManager couchBaseCollectionManager;

    @DocumentDatabase(DocumentDatabaseType.MONGODB)
    @Produces
    @ApplicationScoped
    public DocumentRepository getMongoDB() {

        return producer.get(getMongoManager());
    }

    @DocumentDatabase(DocumentDatabaseType.COUCHBASE)
    @Produces
    @ApplicationScoped
    public DocumentRepository getCouchbaseDB() {
        CouchbaseDocumentConfiguration configuration = new CouchbaseDocumentConfiguration();
        CouhbaseDocumentCollectionManagerFactory factory = configuration.get();
        CouchbaseDocumentCollectionManager couchBaseCollectionManager = factory.get("default");
        return producer.get(couchBaseCollectionManager);
    }

    private CouchbaseDocumentCollectionManager getCouchbaseManager() {
        if (Objects.isNull(couchBaseCollectionManager)) {
            CouchbaseDocumentConfiguration configuration = new CouchbaseDocumentConfiguration();
            CouhbaseDocumentCollectionManagerFactory factory = configuration.get();
            couchBaseCollectionManager = factory.get(DATABASE);
        }
        return couchBaseCollectionManager;
    }

    private MongoDBDocumentCollectionManager getMongoManager() {
        if (Objects.isNull(mongoDBCollectionManager)) {
            MongoDBDocumentConfiguration configuration = new MongoDBDocumentConfiguration();
            MongoDBDocumentCollectionManagerFactory mongoDBFactory = configuration.get();
            mongoDBCollectionManager = mongoDBFactory.get("default");
        }

        return mongoDBCollectionManager;
    }


}
