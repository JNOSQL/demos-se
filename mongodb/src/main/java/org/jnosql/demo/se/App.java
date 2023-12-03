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


import jakarta.data.repository.Page;
import jakarta.data.repository.Pageable;
import jakarta.data.repository.Sort;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class App {


    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            BeerRepository repository = container.select(BeerRepository.class).get();

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
    }

    private App() {
    }
}
