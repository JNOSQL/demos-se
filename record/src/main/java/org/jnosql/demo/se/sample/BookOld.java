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

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Id;

import java.time.Year;

/**
 * This is the {@link org.jnosql.demo.se.Book} without using record feature.
 */
public class BookOld {

    @Id
    private final String isbn;

    @Column
    private final String name;

    @Column
    private final String author;

    @Column
    private final Year year;

    @Column
    private final int edition;

    BookOld(@Id String isbn, @Column("title") String name,
            @Column("author") String author, @Column("year") Year year,
            @Column("edition") int edition) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.year = year;
        this.edition = edition;
    }
}
