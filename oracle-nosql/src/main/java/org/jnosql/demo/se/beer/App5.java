package org.jnosql.demo.se.beer;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

public class App5 {

    public static void main(String[] args) {
        var faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            var repository = container.select(MachineRepository.class, DatabaseQualifier.ofDocument()).get();
            System.out.println(repository.findByType("electric"));

        }

        System.exit(0);
    }
}
