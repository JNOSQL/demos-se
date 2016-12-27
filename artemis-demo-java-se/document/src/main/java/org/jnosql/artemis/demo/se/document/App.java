package org.jnosql.artemis.demo.se.document;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.document.DocumentRepository;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

import java.util.Arrays;
import java.util.Optional;

public class App {

    private static final Person PERSON = Person.builder().
            withPhones(Arrays.asList("234", "432"))
            .withName("Name")
            .withId(1)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer weldContainer = weld.initialize()) {
            DocumentRepository crudOperation = weldContainer.instance().select(DocumentRepository.class).get();
            Person saved = crudOperation.save(PERSON);
            System.out.println("Person saved" + saved);

            DocumentQuery query = DocumentQuery.of("Person");
            query.addCondition(DocumentCondition.eq(Document.of("_id", 1L)));

            Optional<Person> person = crudOperation.singleResult(query);
            System.out.println("Entity found: " + person);

        }
    }
}
