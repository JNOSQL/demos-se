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

import java.util.List;

@Entity
public class God {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private List<String> powers;

    God(String name, List<String> powers) {
        this.name = name;
        this.powers = powers;
    }

    public God() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPowers() {
        return powers;
    }

    @Override
    public String toString() {
        return "God{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", powers=" + powers +
                '}';
    }
}
