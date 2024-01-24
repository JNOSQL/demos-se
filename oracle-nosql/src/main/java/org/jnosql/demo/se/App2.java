package org.jnosql.demo.se;


import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.nosql.keyvalue.KeyValueTemplate;
import net.datafaker.Faker;

public class App2 {

    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            KeyValueTemplate template = container.select(KeyValueTemplate.class).get();
            Beer beer = Beer.of(faker);
            template.put(beer);

            System.out.println("The query result: " + template.get(beer.id(), Beer.class));
            template.delete(beer.id());
            System.out.println("The query result: " + template.get(beer.id(), Beer.class));
        }
        System.exit(0);
    }
}
