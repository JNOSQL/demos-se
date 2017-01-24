package org.jnosql.artemis.demo.se.key;


import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.hazelcast.key.HazelCastKeyValueConfiguration;
import org.jnosql.diana.hazelcast.key.HazelCastKeyValueEntityManagerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class HazelCastProducer {

    private static final String BUCKET = "developers";

    private HazelCastKeyValueConfiguration configuration;

    private HazelCastKeyValueEntityManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new HazelCastKeyValueConfiguration();
        managerFactory = configuration.get();
    }


    @Produces
    public BucketManager getManager() {
        return managerFactory.getBucketManager(BUCKET);

    }

}
