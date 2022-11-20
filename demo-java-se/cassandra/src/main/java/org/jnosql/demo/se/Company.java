/*
 * Copyright (c) 2021 Otávio Santana and others
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
package org.jnosql.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.jnosql.mapping.cassandra.column.UDT;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
public class Company {

    @Id("name")
    private String name;

    @Column
    @UDT("money")
    private Money cost;

    @Column
    private Set<String> languages;

    @Column
    private Map<String, String> contacts;

    @Column
    @UDT("headquarter")
    private Set<Headquarter> headquarters;

    /**
     * Use the {@link Company#builder()} instead
     */
    @Deprecated
    Company() {
    }

    private Company(String name, Money cost, Set<String> languages, Map<String, String> contacts,
                    Set<Headquarter> headquarters) {
        this.name = name;
        this.cost = cost;
        this.languages = languages;
        this.contacts = contacts;
        this.headquarters = headquarters;
    }

    public String getName() {
        return name;
    }

    public Money getCost() {
        return cost;
    }

    public Set<String> getLanguages() {
        if (this.languages == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.languages);
    }

    public Map<String, String> getContacts() {
        if (this.contacts == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(contacts);
    }

    public Set<Headquarter> getAddresses() {
        if (this.headquarters == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.headquarters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", languages=" + languages +
                ", contacts=" + contacts +
                ", addresses=" + headquarters +
                '}';
    }

    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }


    public static class CompanyBuilder {
        private String name;

        private Money cost;

        private Set<String> languages = new HashSet<>();

        private Map<String, String> contacts = new HashMap<>();

        private Set<Headquarter> headquarters = new HashSet<>();

        private CompanyBuilder() {
        }

        public CompanyBuilder withName(String name) {
            this.name = Objects.requireNonNull(name, "name is required");
            return this;
        }

        public CompanyBuilder withCost(Money cost) {
            this.cost = Objects.requireNonNull(cost, "cost is required");
            return this;
        }

        public CompanyBuilder addLanguage(String language) {
            this.languages.add(Objects.requireNonNull(language, "language is required"));
            return this;
        }

        public CompanyBuilder add(String type, String contact) {
            this.contacts.put(Objects.requireNonNull(type, "type is required"),
                    Objects.requireNonNull(contact, "contact is required"));
            return this;
        }

        public CompanyBuilder addHeadquarter(Headquarter headquarter) {
            this.headquarters.add(Objects.requireNonNull(headquarter, "headquarter is required"));
            return this;
        }

        public Company build() {
            return new Company(name, cost, languages, contacts, headquarters);
        }
    }
}
