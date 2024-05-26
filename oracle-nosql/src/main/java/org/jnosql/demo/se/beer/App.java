/*
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package org.jnosql.demo.se.beer;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.document.DocumentTemplate;
import net.datafaker.Faker;

public class App {
    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            for (int index = 0; index < 100; index++) {
                Beer beer = Beer.of(faker);
                template.insert(beer);
            }

            template.select(Beer.class).stream().toList().forEach(System.out::println);

            System.out.println("Selecting malt by Chocolate");
            template.select(Beer.class).where("malt").eq("Chocolate").orderBy("malt").desc()
                    .stream().forEach(System.out::println);

            System.out.println("Selecting style by Stout");
            template.select(Beer.class).where("style").eq("Stout").orderBy("malt").desc()
                    .stream().forEach(System.out::println);
        }

        System.exit(0);
    }
    private App() {
    }
}