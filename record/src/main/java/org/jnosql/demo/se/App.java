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

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

public class App {


    public static void main(String[] args) {

        String id = UUID.randomUUID().toString();
        long superStart = System.currentTimeMillis();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            long start = System.currentTimeMillis();
            Template template = container.select(Template.class).get();
            Book book = new Book(id, "cool", "Otavio", Year.now());
            template.insert(book);

            Optional<Book> optional = template.find(Book.class, id);
            System.out.println("The result " + optional);
            long end = System.currentTimeMillis() - start;
            System.out.println("The total operation is: " + end);
            template.delete(Book.class, id);

        }
        long end = System.currentTimeMillis() - superStart;
        System.out.println("Te total process is: " + end);
    }

    private App() {
    }
}
