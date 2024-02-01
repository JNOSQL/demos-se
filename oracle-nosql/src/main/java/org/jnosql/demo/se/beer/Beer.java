/*
 *  Copyright (c) 2024 Contributors to the Eclipse Foundation
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.jnosql.demo.se.beer;


import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import net.datafaker.Faker;

import java.util.Objects;
import java.util.UUID;
import java.util.List;
import java.util.Map;


@Entity
public class Beer {

    @Id
    private String id;

    @Column
    private String style;

    @Column
    private String hop;

    @Column
    private String malt;

    @Column
    private List<String> comments;

    @Column
    private List<Crew> crew;

    @Column
    private Map<String, Object> data;


    public String id() {
        return id;
    }

    public String style() {
        return style;
    }

    public String hop() {
        return hop;
    }

    public String malt() {
        return malt;
    }

    public List<String> comments() {
        return comments;
    }

    public List<Crew> crew() {
        return crew;
    }

    public Map<String, Object> data() {
        return data;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                ", style='" + style + '\'' +
                ", hop='" + hop + '\'' +
                ", malt='" + malt + '\'' +
                ", comments='" + comments + '\'' +
                ", crew='" + crew + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Beer beer = (Beer) object;
        return Objects.equals(id, beer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static Beer of(Faker faker){
        var beer = faker.beer();
        Beer entity = new Beer();
        entity.hop = beer.hop();
        entity.malt = beer.malt();
        entity.style = beer.style();
        entity.id= UUID.randomUUID().toString();
        entity.comments  = List.of("comment1", "comment2");
        entity.crew  = List.of(new Crew("Otavio"));
        entity.data = Map.of("name", "beer", "price", 50);
        return entity;
    }

}