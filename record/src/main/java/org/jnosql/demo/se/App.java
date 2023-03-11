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


import jakarta.nosql.mapping.Template;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

public class App {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            String id = UUID.randomUUID().toString();
            long start = System.currentTimeMillis();
            Template template = container.select(Template.class).get();
            Book book = new Book(id, "Java Concurrency in Practice", " Brian Goetz", Year.of(2006), 1);
            template.insert(book);
            Optional<Book> optional = template.find(Book.class, id);
            System.out.println("The result " + optional);
            long end = System.currentTimeMillis() - start;
            System.out.println("The total operation is: " + end);
            template.delete(Book.class, id);
        }
    }

    private App() {
    }
}
