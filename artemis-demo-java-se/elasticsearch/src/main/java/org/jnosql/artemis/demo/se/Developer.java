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


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

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
        if (!(o instanceof Developer)) {
            return false;
        }
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Developer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phones=").append(phones);
        sb.append(", languages=").append(languages);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }

    public static DeveloperBuilder builder() {
        return new DeveloperBuilder();
    }
}
