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
import jakarta.nosql.mapping.Entity;

import java.util.Objects;

@Entity
public class Headquarter {

    @Column
    private String city;

    @Column
    private String country;

    @Deprecated
    Headquarter() {
    }

    private Headquarter(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Headquarter headquarter = (Headquarter) o;
        return Objects.equals(city, headquarter.city) && Objects.equals(country, headquarter.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country);
    }

    @Override
    public String toString() {
        return "Headquarter{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static Headquarter of(String city, String country) {
        return new Headquarter(Objects.requireNonNull(city, "city is required"),
                Objects.requireNonNull(country, "country is required"));
    }
}
