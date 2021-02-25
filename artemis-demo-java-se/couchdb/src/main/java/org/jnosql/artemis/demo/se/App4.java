package org.jnosql.artemis.demo.se;

import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static jakarta.nosql.document.DocumentQuery.select;

public class App4 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Map<String, Object> maps = Collections.singletonMap("1", "2");
            UserScopePropertiesBroken entity1 = new UserScopePropertiesBroken("user", "scope", maps);

            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.insert(entity1);

            final DocumentQuery query1 = select().from("user_scope_properties_broken").where("_id")
                    .eq("user").and("scope").eq("scope").build();
            final Optional<Object> first1 = template.select(query1).findFirst();
            System.out.println(first1);
        }
    }
}
