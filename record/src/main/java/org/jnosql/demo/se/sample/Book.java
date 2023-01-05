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
package org.jnosql.demo.se.sample;

import java.time.Year;

/**
 * This is the {@link org.jnosql.demo.se.Book} without using record feature.
 */
public class Book {

    private final String id;

    private final String name;

    private final String author;

    private final Year year;

    public Book(String id, String name, String author, Year year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
}
