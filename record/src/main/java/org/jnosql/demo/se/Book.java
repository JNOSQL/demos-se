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

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Convert;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import java.time.Year;
import java.util.Objects;

@Entity
public record Book(@Id String isbn,
                   @Column("title") String title,
                   @Column("author") String author,
                   @Convert(YearConverter.class) @Column("year") Year year,
                   @Column("edition") int edition) {


    public Book nextEdition(String isbn, Year year) {
        Objects.requireNonNull(isbn, "isbn is required");
        Objects.requireNonNull(year, "year is required");
        return new Book(isbn, this.title, this.author, year, this.edition + 1);
    }

}
