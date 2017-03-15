package org.jnosql.artemis.demo.se.document;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.DatabaseQualifier;
import org.jnosql.artemis.document.DocumentRepository;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App3 {


    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer weldContainer = weld.initialize()) {
            Random random = new Random();
            long id = random.nextLong();
            Person person = Person.builder().
                    withPhones(Arrays.asList("234", "432"))
                    .withName("Name")
                    .withId(id)
                    .withIgnore("Just Ignore")
                    .withAddress(new Address("Engenheiro Jose Anasoh", "Salvador", 53))
                    .build();

            DocumentRepository repository = weldContainer.instance().select(DocumentRepository.class).get();
            Person saved = repository.save(person);
            System.out.println("Person saved" + saved);

            DocumentQuery query = DocumentQuery.of("Person");
            query.and(DocumentCondition.eq(Document.of("_id", id)));

            List<Person> people = repository.find(query);
            System.out.println("Entity found: " + people);

        }
    }
}
