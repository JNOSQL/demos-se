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

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Company {

    @Id("name")
    private String name;

    @Column(udt = "headquarter")
    private Set<Headquarter> headquarters;

    /**
     * Use the {@link Company#builder()} instead
     */
    @Deprecated
    Company() {
    }

    private Company(String name, Set<Headquarter> headquarters) {
        this.name = name;
        this.headquarters = headquarters;
    }

    public String getName() {
        return name;
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
                ", headquarters=" + headquarters +
                '}';
    }

    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }


    public static class CompanyBuilder {
        private String name;

        private final Set<Headquarter> headquarters = new HashSet<>();

        private CompanyBuilder() {
        }

        public CompanyBuilder name(String name) {
            this.name = Objects.requireNonNull(name, "name is required");
            return this;
        }

        public CompanyBuilder add(Headquarter headquarter) {
            this.headquarters.add(Objects.requireNonNull(headquarter, "headquarter is required"));
            return this;
        }

        public Company build() {
            return new Company(name, headquarters);
        }
    }
}
