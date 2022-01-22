/*
 * Copyright (c) 2017 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */

package org.jnosql.artemis.demo.se;


import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import java.util.List;


@Entity("Person")
public class Person {

    @Id("id")
    private long id;

    @Column
    private String name;

    @Column
    private List<String> phones;

    private String ignore;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<String> getPhones() {
        return phones;
    }

    public String getIgnore() {
        return ignore;
    }

    public Person() {
    }

    Person(long id, String name, List<String> phones, String ignore) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                ", ignore='" + ignore + '\'' +
                '}';
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }
}
