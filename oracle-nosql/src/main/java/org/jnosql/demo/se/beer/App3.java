package org.jnosql.demo.se.beer;

import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

public class App3 {

    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            BeerRepository repository = container.select(BeerRepository.class, DatabaseQualifier.ofDocument()).get();
            for (int index = 0; index < 100; index++) {
                Beer beer = Beer.of(faker);
                repository.save(beer);

            }

            PageRequest pageRequest = PageRequest.ofPage(1);
            Page<Beer> page1 = repository.findAll(pageRequest, Order.by(Sort.desc("style")));
            System.out.println("The first pageRequest");
            page1.forEach(System.out::println);
            System.out.println("The second pageRequest");
            PageRequest secondPage = page1.nextPageRequest();
            Page<Beer> page2 = repository.findAll(secondPage, Order.by(Sort.desc("style")));
            page2.forEach(System.out::println);

            System.out.println("The query result: ");
            repository.query().forEach(System.out::println);
        }

        System.exit(0);
    }
}
