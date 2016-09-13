package org.jnosql.diana.jsr363.producers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.jnosql.diana.api.column.ColumnConfiguration;
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.api.column.ColumnFamilyManagerFactory;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;

@ApplicationScoped
class ColumnFamilyFactory {

    private static final String KEY_SPACE = "jsr363";

    private ColumnFamilyManagerFactory managerFactory;

    @PostConstruct
    public void setup() {
        ColumnConfiguration configuration = new CassandraConfiguration();
        managerFactory = configuration.getManagerFactory();
    }


    @Produces
    public ColumnFamilyManagerFactory getManagerFactory() {
        return managerFactory;
    }

    @Produces
    public ColumnFamilyManager getManager() {
        return managerFactory.getColumnEntityManager(KEY_SPACE);
    }

}
