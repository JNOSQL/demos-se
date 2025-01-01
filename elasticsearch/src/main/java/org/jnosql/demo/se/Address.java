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

import jakarta.nosql.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private Integer number;


    Address(String street, String city, Integer number) {
        this.street = street;
        this.city = city;
        this.number = number;
    }

    Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(number, address.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, number);
    }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", number=" + number +
                '}';
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }
}
