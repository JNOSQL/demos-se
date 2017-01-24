package org.jnosql.diana.jsr363.producers;

import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
class DocumentCollectionFactory {

    private DocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void setup() {
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        managerFactory = configuration.get();
    }


    @Produces
    public DocumentCollectionManagerFactory getManagerFactory() {
        return managerFactory;
    }

    @Produces
    public DocumentCollectionManager getManager() {
        return managerFactory.get("jsr363-demo");
    }

}
