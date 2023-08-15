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


import com.github.javafaker.Faker;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class App2 {


    public static void main(String[] args) {

        var faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
        ProductRepository repository = container.select(ProductRepository.class).get();

            for (int index = 0; index < 10; index++) {
                Product product = Product.of(faker);
                repository.save(product);
            }
            System.out.println("Find all");
            repository.findAll().forEach(System.out::println);

            System.out.println("Find by name");
            repository.findByName("Gorgeous Leather Shoes").forEach(System.out::println);
            System.out.println("Find by category");
            repository.findByCategory("Computers").forEach(System.out::println);

            System.out.println("Find by material");
            repository.findByMaterial("Steel").forEach(System.out::println);
        }
    }

    private App2() {
    }
}
