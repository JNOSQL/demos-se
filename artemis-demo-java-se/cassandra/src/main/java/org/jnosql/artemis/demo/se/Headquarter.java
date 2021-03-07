package org.jnosql.artemis.demo.se;

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
