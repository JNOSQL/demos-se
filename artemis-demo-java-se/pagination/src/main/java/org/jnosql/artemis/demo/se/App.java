/*
 * Copyright (c) 2017 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */

package org.jnosql.artemis.demo.se;


import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

import static jakarta.nosql.document.DocumentDeleteQuery.delete;

public class App {


    public static void main(String[] args) throws IOException {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Supplier<List<String>> querySupplier = container.select(QuerySupplier.class).get();
            template.delete(delete().from("Car").build());
            final List<String> queries = querySupplier.get();
            queries.forEach(template::query);

            final List<Car> cars = template.query("select * from Car order by provider asc");
            System.out.println(cars.size());


        }
    }


    private App() {
    }
}
