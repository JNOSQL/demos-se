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

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import org.eclipse.jnosql.mapping.vector.Vector;

@Entity
public class Product {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private ProductType type;

    @Column
    private Vector embedding;

    Product(String id, String name, String description, ProductType type, Vector embedding) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.embedding = embedding;
    }

    Product() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductType getType() {
        return type;
    }

    public Vector getEmbedding() {
        return embedding;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
