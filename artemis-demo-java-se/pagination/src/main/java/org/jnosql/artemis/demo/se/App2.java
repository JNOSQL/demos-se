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


import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.Page;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.document.DocumentQueryPagination;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;
import java.util.function.Supplier;

import static jakarta.nosql.document.DocumentDeleteQuery.delete;

public class App2 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Supplier<List<String>> querySupplier = container.select(QuerySupplier.class).get();
            template.delete(delete().from("Car").build());
            final List<String> queries = querySupplier.get();
            queries.forEach(template::query);

            DocumentQuery query = DocumentQuery.select().from("Car").orderBy("provider").asc().build();
            Pagination pagination = Pagination.page(1).size(2);
            DocumentQueryPagination queryPagination = DocumentQueryPagination.of(query, pagination);

            final Page<Car> page = template.select(queryPagination);
            List<Car> cars = page.getContent();
            System.out.println(cars.size());
            final Page<Car> nextPage = page.next();
            cars = nextPage.getContent();
            System.out.println(cars.size());
        }
    }

    private App2() {
    }
}
