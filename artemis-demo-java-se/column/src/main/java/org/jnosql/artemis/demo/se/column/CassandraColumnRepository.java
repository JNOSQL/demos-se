package org.jnosql.artemis.demo.se.column;


import com.datastax.driver.core.ConsistencyLevel;
import org.jnosql.artemis.column.AbstractColumnRepository;
import org.jnosql.artemis.column.ColumnEntityConverter;
import org.jnosql.artemis.column.ColumnWorkflow;
import org.jnosql.diana.api.column.ColumnDeleteQuery;
import org.jnosql.diana.api.column.ColumnEntity;
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.cassandra.column.CassandraColumnFamilyManager;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class CassandraColumnRepository extends AbstractColumnRepository {

    @Inject
    private ColumnEntityConverter converter;
    @Inject
    private CassandraColumnFamilyManager manager;
    @Inject
    private ColumnWorkflow workflow;

    @Override
    protected ColumnEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected ColumnFamilyManager getManager() {
        return manager;
    }

    @Override
    protected ColumnWorkflow getFlow() {
        return workflow;
    }

    public <T> T save(T entity, ConsistencyLevel level) {
        Objects.requireNonNull(entity, "entity is required");
        Objects.requireNonNull(level, "level is required");
        UnaryOperator<ColumnEntity> save = e -> manager.save(e, level);
        return workflow.flow(entity, save);
    }

    public <T> T save(T entity, Duration ttl, ConsistencyLevel level) throws NullPointerException {
        Objects.requireNonNull(entity, "entity is required");
        Objects.requireNonNull(level, "level is required");
        UnaryOperator<ColumnEntity> save = e -> manager.save(e, ttl, level);
        return workflow.flow(entity, save);
    }

    public <T> List<T> cql(String query) throws NullPointerException {
        Objects.requireNonNull(query, "query is required");
        List<ColumnEntity> entities = manager.cql(query);
        return entities.stream().map(converter::toEntity).map(c -> (T) c)
                .collect(Collectors.toList());
    }

    public void delete(ColumnDeleteQuery query, ConsistencyLevel level) throws NullPointerException {
        manager.delete(query, level);
    }

}
