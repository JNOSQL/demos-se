package org.jnosql.artemis.demo.se.column;


import com.datastax.driver.core.ConsistencyLevel;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.cassandra.column.CassandraColumnRepository;
import org.jnosql.artemis.column.ColumnRepository;
import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App3 {

    private static final Person PERSON = Person.builder().
            withPhones(Arrays.asList("234", "432"))
            .withName("Name")
            .withId(1)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try(WeldContainer weldContainer = weld.initialize()) {
            CassandraColumnRepository crudOperation = weldContainer.instance().select(CassandraColumnRepository.class).get();
            Person saved = crudOperation.save(PERSON, ConsistencyLevel.ONE);
            System.out.println("Person saved" + saved);

            ColumnQuery query = ColumnQuery.of("Person");
            query.and(ColumnCondition.eq(Column.of("id", 1L)));

            List<Person> people = crudOperation.cql("select * from developers.Person where id = 1");
            System.out.println("Entity found: " + people);

        }
    }
}
