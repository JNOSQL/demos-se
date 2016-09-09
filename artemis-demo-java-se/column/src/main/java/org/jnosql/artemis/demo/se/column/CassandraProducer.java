package org.jnosql.artemis.demo.se.column;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;
import org.jnosql.diana.cassandra.column.CassandraDocumentEntityManagerFactory;

@ApplicationScoped
public class CassandraProducer {

    private static final String KEY_SPACE = "developers";

    private CassandraConfiguration cassandraConfiguration;

    private CassandraDocumentEntityManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        cassandraConfiguration = new CassandraConfiguration();
        managerFactory = cassandraConfiguration.getManagerFactory();
    }


    @Produces
    public ColumnFamilyManager getManager() {
        return managerFactory.getColumnEntityManager(KEY_SPACE);
    }

}
