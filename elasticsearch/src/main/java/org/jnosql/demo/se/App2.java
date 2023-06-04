/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package org.jnosql.demo.se;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App2 {


    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        long id = random.nextLong();

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Address address = Address.builder()
                    .withCity("São Paulo")
                    .withStreet("Av. nove de Julho 1854")
                    .withNumber(10).build();

            Developer developer = Developer.builder().
                    withPhones(Arrays.asList("85 85 343435684", "55 11 123448684"))
                    .withName("Maria Lovelace")
                    .withId(id)
                    .withAddress(address)
                    .build();

            DeveloperRepository repository = container.select(DeveloperRepository.class)
                    .select(DatabaseQualifier.ofDocument()).get();
            repository.save(developer);

            // it's needed 'cause the cluster indexing requires a little delay
            TimeUnit.SECONDS.sleep(2);

            List<Developer> people = repository.findByName("Maria Lovelace");
            System.out.println("Entity found: " + people);

        }
    }

    private App2() {
    }
}
