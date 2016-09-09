package org.jnosql.artemis.demo.se.column;


import java.util.Arrays;
import java.util.Optional;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.column.ColumnCrudOperation;
import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnQuery;

public class App {

    private static final Person PERSON = Person.builder().
            withPhones(Arrays.asList("234", "432"))
            .withName("Name")
            .withId(1)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try(WeldContainer weldContainer = weld.initialize()) {
            ColumnCrudOperation crudOperation = weldContainer.instance().select(ColumnCrudOperation.class).get();
            Person saved = crudOperation.save(PERSON);
            System.out.println("Person saved" + saved);

            ColumnQuery query = ColumnQuery.of("Person");
            query.addCondition(ColumnCondition.eq(Column.of("id", 1L)));

            Optional<Person> person = crudOperation.singleResult(query);
            System.out.println("Entity found: " + person);

        }
    }
}
