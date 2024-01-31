package org.jnosql.demo.se;


import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;

public class App3 {

    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            BeerRepository repository = container.select(BeerRepository.class).get();
            for (int index = 0; index < 100; index++) {
                Beer beer = Beer.of(faker);
                repository.save(beer);

            }

            Pageable page = Pageable.ofPage(1).sortBy(Sort.desc("style"));
            Page<Beer> page1 = repository.findAll(page);
            System.out.println("The first page");
            page1.forEach(System.out::println);
            System.out.println("The second page");
            Pageable secondPage = page.next();
            Page<Beer> page2 = repository.findAll(secondPage);
            page2.forEach(System.out::println);

            System.out.println("The query result: ");
            repository.query().forEach(System.out::println);
        }

        System.exit(0);
    }
}
