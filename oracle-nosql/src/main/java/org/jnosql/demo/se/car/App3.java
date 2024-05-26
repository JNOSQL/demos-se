package org.jnosql.demo.se.car;


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
            Garage repository = container.select(Garage.class, DatabaseQualifier.ofDocument()).get();
            for (int index = 0; index < 0; index++) {
                Car car = Car.of(faker);
                repository.parking(car);
            }

            Car car = Car.of(faker);
            repository.parking(car);
            repository.unpark(car);

            PageRequest pageRequest = PageRequest.ofPage(1).size(3);
            Page<Car> page1 = repository.findByTransmission("CVT", pageRequest);
            System.out.println("The first pageRequest");
            page1.forEach(System.out::println);
            System.out.println("The second pageRequest");
            var secondPage = page1.nextPageRequest();
            Page<Car> page2 = repository.findByTransmission("CVT", secondPage);
            page2.forEach(System.out::println);

            System.out.println("The query result: ");
        }

        System.exit(0);
    }
}
