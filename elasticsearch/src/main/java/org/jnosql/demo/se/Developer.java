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
import java.util.Objects;

import static java.util.Collections.unmodifiableList;


@Entity("developer")
public class Developer {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private List<String> phones;

    @Column
    private List<String> languages;

    @Column
    private Address address;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<String> getPhones() {
        return unmodifiableList(phones);
    }

    public List<String> getLanguages() {
        return unmodifiableList(languages);
    }

    Developer() {
    }

    Developer(long id, String name, List<String> phones, List<String> languages,
              Address address) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.languages = languages;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer developer)) {
            return false;
        }
        return Objects.equals(id, developer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Developer{" + "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                ", languages=" + languages +
                ", address=" + address +
                '}';
    }

    public static DeveloperBuilder builder() {
        return new DeveloperBuilder();
    }
}
