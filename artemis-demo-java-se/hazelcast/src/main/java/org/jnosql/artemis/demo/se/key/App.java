package org.jnosql.artemis.demo.se.key;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jnosql.artemis.key.KeyValueRepository;

import java.util.Arrays;
import java.util.Optional;

public class App {

    private static final User USER = User.builder().
            withPhones(Arrays.asList("234", "432"))
            .withUsername("username")
            .withName("Name")
            .build();

    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer weldContainer = weld.initialize()) {
            KeyValueRepository repository = weldContainer.instance().select(KeyValueRepository.class).get();
            User saved = repository.put(USER);
            System.out.println("User saved" + saved);
            Optional<User> person = repository.get("username", User.class);
            System.out.println("Entity found: " + person);

        }
    }
}
