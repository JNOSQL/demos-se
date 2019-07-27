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


import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;
import java.util.function.Supplier;

import static jakarta.nosql.document.DocumentDeleteQuery.delete;

public class App4 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            CarRepository repository = container.select(CarRepository.class).get();
            Supplier<List<String>> querySupplier = container.select(QuerySupplier.class).get();
            template.delete(delete().from("Car").build());
            final List<String> queries = querySupplier.get();
            queries.forEach(template::query);

            Pagination pagination = Pagination.page(1).size(2);
            final List<Car> cars = repository.findByOrderByProvider(pagination);
            System.out.println(cars.size());
        }
    }
    private App4() {
    }
}
