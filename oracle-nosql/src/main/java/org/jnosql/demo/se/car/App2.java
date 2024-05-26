package org.jnosql.demo.se.car;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.keyvalue.KeyValueTemplate;
import net.datafaker.Faker;

public class App2 {

    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            KeyValueTemplate template = container.select(KeyValueTemplate.class).get();
            Car car = Car.of(faker);
            template.put(car);

            System.out.println("The query result: " + template.get(car.id(), Car.class));
            template.delete(car.id());
            System.out.println("The query result: " + template.get(car.id(), Car.class));
        }
        System.exit(0);
    }
}
