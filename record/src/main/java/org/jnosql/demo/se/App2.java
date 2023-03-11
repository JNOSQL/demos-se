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


import org.eclipse.jnosql.communication.document.DocumentQuery;
import jakarta.nosql.document.DocumentTemplate;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import java.time.Year;
import java.util.List;

import static java.util.UUID.randomUUID;

public class App2 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Book first = new Book(randomUUID().toString(), "Effective Java", "Joshua Bloch",
                    Year.of(2001), 1);
            Book second = first.nextEdition(randomUUID().toString(), Year.of(2008));
            Book third = second.nextEdition(randomUUID().toString(), Year.of(2018));
            template.insert(List.of(first, second, third));
            DocumentQuery query = DocumentQuery.select().from("Book")
                    .where("title").eq("Effective Java")
                    .orderBy("year").desc().build();

            System.out.println("The Effective java editions: ");
            template.select(Book.class)
                    .where("title").eq("Effective Java")
                    .orderBy("year").desc()
                    .stream()
                    .forEach(System.out::println);

            template.delete(Book.class, first.isbn());
            template.delete(Book.class, second.isbn());
            template.delete(Book.class, third.isbn());

        }
    }

    private App2() {
    }
}
