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
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class App {


    public static void main(String[] args) {

        ThreadLocalRandom random = ThreadLocalRandom.current();
        long id = random.nextLong(1, 1_000_000);
        long superStart = System.currentTimeMillis();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            long start = System.currentTimeMillis();
            Template template = container.select(Template.class).get();
            Book book = new Book(id, "cool");
            template.insert(book);

            Optional<Book> optional = template.find(Book.class, id);
            System.out.println("The result " + optional);
            long end = System.currentTimeMillis() - start;
            System.out.println("The total operation is: " + end);

        }
        long end = System.currentTimeMillis() - superStart;
        System.out.println("Te total process is: " + end);
    }

    private App() {
    }
}
