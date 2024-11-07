package org.jnosql.demo.se.beer;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

public class App6 {

    public static void main(String[] args) {
        var faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            var guest = new Guest(faker.cpf().valid(), faker.name().name());
            var room = new Room(12, guest);
            var repository = container.select(Hotel.class, DatabaseQualifier.ofDocument()).get();
            var roomUpdated = repository.checkIn(room);
            System.out.println("Room updated: " + roomUpdated);

        }

        System.exit(0);
    }
}
