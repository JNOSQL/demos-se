package org.jnosql.artemis.demo.se;

import jakarta.nosql.NonUniqueResultException;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static jakarta.nosql.document.DocumentQuery.select;

@ApplicationScoped
public class GreekService {


    @Inject
    @ConfigProperty(name = "db1")
    private DocumentTemplate template;

    public God insert(God god) {
        return template.insert(god);
    }

    public God findName(String name) {
        DocumentQuery query = select().from("God")
                .where("name")
                .eq(name).build();

        return template.<God>select(query).findFirst()
                .orElseThrow(() -> new NonUniqueResultException("There is not Greek god in the name: " + name));
    }
}
