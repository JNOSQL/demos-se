package org.jnosql.artemis.demo.se.document;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.DatabaseQualifier;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

import java.util.Arrays;
import java.util.List;

public class App2 {

    private static final Person PERSON = Person.builder().
            withPhones(Arrays.asList("234", "432"))
            .withName("Name")
            .withId(2)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer weldContainer = weld.initialize()) {
            PersonRepository repository = weldContainer.instance().select(PersonRepository.class)
                    .select(DatabaseQualifier.ofDocument()).get();
            Person saved = repository.save(PERSON);
            System.out.println("Person saved" + saved);

            DocumentQuery query = DocumentQuery.of("Person");
            query.and(DocumentCondition.eq(Document.of("_id", 1L)));

            List<Person> people = repository.findByName("Name");
            System.out.println("Entity found: " + people);

        }
    }
}
