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

package org.jnosql.demo.se.car;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.document.DocumentTemplate;
import net.datafaker.Faker;


public class App {


    public static void main(String[] args) {
        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            for (int index = 0; index < 10; index++) {
                Car car = Car.of(faker);
                template.insert(car);
            }

            template.select(Car.class).stream().toList().forEach(System.out::println);
            template.select(Car.class).where("transmission").eq("Automatic").orderBy("model").desc()
                    .stream().forEach(System.out::println);

            template.select(Car.class).where("transmission").eq("CVT").orderBy("make").desc()
                    .stream().forEach(System.out::println);

        }
        System.exit(0);
    }

    private App() {
    }
}
