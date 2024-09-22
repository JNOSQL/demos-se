package org.jnosql.demo.se.beer;

import jakarta.data.page.PageRequest;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;

public class App4 {

    public static void main(String[] args) {
        var faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            BeerRepository repository = container.select(BeerRepository.class).get();
            for (int index = 0; index < 100; index++) {
                Beer beer = Beer.of(faker);
                repository.save(beer);

            }

            PageRequest pageRequest = PageRequest.ofSize(3);
            var page1 = repository.style("Stout", pageRequest);
            System.out.println("Page 1");
            page1.forEach(System.out::println);

            PageRequest pageRequest2 = page1.nextPageRequest();
            var page2 = repository.style("Stout", pageRequest2);
            System.out.println("Page 2");
            page2.forEach(System.out::println);

            System.out.println("JDQL query: ");

            repository.jpql("Stout").forEach(System.out::println);
        }

        System.exit(0);
    }
}
