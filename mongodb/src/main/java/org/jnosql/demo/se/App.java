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



import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;


public class App {


    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            BeerRepository repository = container.select(BeerRepository.class).get();
            for (int index = 0; index < 100; index++) {
                Beer beer = Beer.of(faker);
                repository.save(beer);
            }

            Order<Beer> order = Order.by(Sort.desc("style"));
            var page = PageRequest.ofPage(1);
            Page<Beer> page1 = repository.findAll(page, order);
            System.out.println("The first page");
            page1.forEach(System.out::println);
            System.out.println("The second page");
            var secondPage = page1.nextPageRequest();
            Page<Beer> page2 = repository.findAll(secondPage, order);
            page2.forEach(System.out::println);

            System.out.println("The query result: ");
            repository.query().forEach(System.out::println);
        }
    }

    private App() {
    }
}
