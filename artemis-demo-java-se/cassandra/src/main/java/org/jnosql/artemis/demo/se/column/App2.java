package org.jnosql.artemis.demo.se.column;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.DatabaseQualifier;

import java.util.Arrays;
import java.util.Optional;

public class App2 {

    private static final Person PERSON = Person.builder().
            withPhones(Arrays.asList("234", "432"))
            .withName("Name")
            .withId(1)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try(WeldContainer weldContainer = weld.initialize()) {
            PersonRepository repository = weldContainer.instance()
                    .select(PersonRepository.class).select(DatabaseQualifier.ofColumn()).get();
            Person saved = repository.save(PERSON);
            System.out.println("Person saved" + saved);

            Optional<Person> person = repository.findByIdOrderByName(1L);
            System.out.println("Entity found: " + person);

        }
    }
}
