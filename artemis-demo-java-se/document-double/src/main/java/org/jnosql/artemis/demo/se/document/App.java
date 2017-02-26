package org.jnosql.artemis.demo.se.document;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.document.DocumentRepository;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.jnosql.artemis.demo.se.document.DocumentDatabaseQualifier.COUCHBASE;
import static org.jnosql.artemis.demo.se.document.DocumentDatabaseQualifier.MONGODB;

public class App {

    private static final List<String> PHONES;
    static {
        PHONES = new ArrayList<>();
        PHONES.add("123456789");
        PHONES.add("234242");
    }
    private static final String ID = UUID.randomUUID().toString();
    private static final Person PERSON = Person.builder().
            withPhones(PHONES)
            .withName("Name")
            .withId(ID)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer weldContainer = weld.initialize()) {

            System.out.println(" Using couchbase database");
            DocumentRepository crudOperation = weldContainer.instance().select(DocumentRepository.class)
                    .select(COUCHBASE).get();

            Person saved = crudOperation.save(PERSON);
            System.out.println("Person saved" + saved);

            DocumentQuery query = DocumentQuery.of("Person");
            query.and(DocumentCondition.eq(Document.of("_id", ID)));

            Optional<Person> person = crudOperation.singleResult(query);
            System.out.println("Entity found: " + person);


            System.out.println(" Using mongodb database");

            crudOperation = weldContainer.instance().select(DocumentRepository.class)
                    .select(MONGODB).get();

            saved = crudOperation.save(PERSON);
            System.out.println("Person saved" + saved);

            query = DocumentQuery.of("Person");
            query.and(DocumentCondition.eq(Document.of("_id", ID)));

            person = crudOperation.singleResult(query);
            System.out.println("Entity found: " + person);

        }
    }
}
