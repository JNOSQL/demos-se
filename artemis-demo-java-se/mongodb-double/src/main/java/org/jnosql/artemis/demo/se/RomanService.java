package org.jnosql.artemis.demo.se;

import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.stream.Stream;

import static jakarta.nosql.document.DocumentQuery.select;

@ApplicationScoped
public class RomanService {

    @Inject
    @ConfigProperty(name = "db2")
    private DocumentTemplate template;

    public God insert(God god) {
        return template.insert(god);
    }

    public Stream<God> findName(String name) {
        DocumentQuery query = select().from("God")
                .where("name")
                .eq(name).build();

        return template.select(query);
    }
}
